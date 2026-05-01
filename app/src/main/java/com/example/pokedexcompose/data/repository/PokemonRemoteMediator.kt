package com.example.pokedexcompose.data.repository

import androidx.core.net.toUri
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.example.pokedexcompose.data.dataBase.local.entities.Pokemon
import com.example.pokedexcompose.data.dataBase.local.entities.PokemonDetail
import com.example.pokedexcompose.data.dataBase.local.entities.PokemonRemoteKey
import com.example.pokedexcompose.data.dataBase.local.entities.PokemonSpecies
import com.example.pokedexcompose.data.dataSource.local.LocalDataSource
import com.example.pokedexcompose.data.dataSource.remote.RemoteDataSource
import com.example.pokedexcompose.data.model.local.PokemonAndDetail
import com.example.pokedexcompose.data.model.remote.PokemonDetailRemote
import com.example.pokedexcompose.data.model.remote.PokemonRemote
import com.example.pokedexcompose.extensions.getUrlId
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext

@OptIn(ExperimentalPagingApi::class)
class PokemonRemoteMediator(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : RemoteMediator<Int, PokemonAndDetail>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PokemonAndDetail>
    ): MediatorResult {
        return try {
            val offSet = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKey = getClosestRemoteKeyToCurrentPosition(state)
                    remoteKey?.nextOffset?.minus(OFFSET) ?: 0
                }
                LoadType.PREPEND -> {
                    val remoteKey = getRemoteKeyForFirstItem(state)
                    val prevKey = remoteKey?.prevOffset ?: return MediatorResult.Success(
                        endOfPaginationReached = remoteKey != null
                    )
                    prevKey
                }
                LoadType.APPEND -> {
                    val remoteKey = getRemoteKeyForLastItem(state)
                    val nextKey = remoteKey?.nextOffset ?: return MediatorResult.Success(
                        endOfPaginationReached = remoteKey != null
                    )
                    nextKey
                }
            }

            withContext(IO) {
                val response = remoteDataSource.getListPokemon(limit = state.pages.size, offset = offSet)
                val pokemonList = response.results
                val endOfPaginationReached = pokemonList.isEmpty()
                val prevKey = getOffsetParameter(response.previous)
                val nextKey = getOffsetParameter(response.next)

                val results = coroutineScope {
                    pokemonList.map { pokemon ->
                        async {
                            fetchFullPokemonData(pokemon, prevKey, nextKey)
                        }
                    }.awaitAll().filterNotNull()
                }

                if (results.isNotEmpty()) {
                    localDataSource.saveAllRemoteKey(results.map { it.remoteKey })
                    localDataSource.saveAllPokemonDetail(results.map { it.detail })
                    localDataSource.saveAllPokemons(results.map { it.pokemon })
                    localDataSource.saveAllPokemonSpecies(results.mapNotNull { it.species })
                }

                MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            MediatorResult.Error(e)
        }
    }

    private suspend fun fetchFullPokemonData(
        pokemon: PokemonRemote,
        prevKey: Int?,
        nextKey: Int?
    ): PokemonFullData? {
        return try {
            val detailRemote = remoteDataSource.getPokemonDetails(pokemon.name)

            val pokemonEntity = Pokemon(
                pokemonId = detailRemote.id,
                name = pokemon.name,
                imageUrl = detailRemote.sprites.other.officialArtwork.frontDefault
            )

            val detailEntity = detailRemote.mapPokeDetailRemoteToPokeDetail()

            val remoteKeyEntity = PokemonRemoteKey(
                id = detailRemote.id.toLong(),
                pokemonName = pokemon.name,
                prevOffset = prevKey,
                nextOffset = nextKey
            )

            val speciesEntity = findSpeciePokemon(detailRemote)

            PokemonFullData(pokemonEntity, detailEntity, remoteKeyEntity, speciesEntity)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    private suspend fun findSpeciePokemon(
        pokemonDetailRemote: PokemonDetailRemote
    ): PokemonSpecies? {
        return pokemonDetailRemote.species?.let { detailRemote ->
            try {
                remoteDataSource.searchPokemonSpecie(pokemonSpecieUrl = detailRemote.url)?.let { species ->
                    species.evolutionChainAddressRemote?.url?.let { evolutionChainUrl ->
                        findEvolutionChainPokemon(evolutionChainUrl)
                    }
                    species.mapToPokemonSpecie()
                }
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
    }

    private suspend fun findEvolutionChainPokemon(evolutionChainUrl: String) {
        if (searchLocalEvolutionChainById(evolutionChainUrl)) {
            try {
                val evolutionChainRemote = remoteDataSource.searchEvolutionChan(evolutionChainUrl)
                localDataSource.saveEvolutionChain(evolutionChainRemote.mapToEvolutionChain())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun searchLocalEvolutionChainById(evolutionChainUrl: String): Boolean {
        val evolutionChainLocal = localDataSource.searchEvolutionChainById(evolutionChainUrl.getUrlId)
        return evolutionChainLocal == null
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, PokemonAndDetail>): PokemonRemoteKey? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { pokemonAndDetail ->
                localDataSource.getPokemonRemoteKeyByName(pokemonAndDetail.pokemon.name)
            }
    }

    private suspend fun getClosestRemoteKeyToCurrentPosition(state: PagingState<Int, PokemonAndDetail>): PokemonRemoteKey? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.pokemon?.name?.let { pokemonName ->
                localDataSource.getPokemonRemoteKeyByName(pokemonName)
            }
        }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, PokemonAndDetail>): PokemonRemoteKey? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { pokemonAndDetail ->
                localDataSource.getPokemonRemoteKeyByName(pokemonAndDetail.pokemon.name)
            }
    }

    fun getOffsetParameter(url: String?): Int? {
        return url?.let {
            url.toUri().getQueryParameter("offset")?.toInt()
        }
    }

    private data class PokemonFullData(
        val pokemon: Pokemon,
        val detail: PokemonDetail,
        val remoteKey: PokemonRemoteKey,
        val species: PokemonSpecies?
    )

    private companion object {
        const val OFFSET = 100
    }
}