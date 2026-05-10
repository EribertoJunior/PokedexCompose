package com.example.pokedexcompose.data.dataSource.local

import androidx.paging.PagingSource
import com.example.pokedexcompose.data.local.entities.EvolutionChainEntity
import com.example.pokedexcompose.data.local.entities.PokemonEntity
import com.example.pokedexcompose.data.local.entities.PokemonDetail
import com.example.pokedexcompose.data.local.entities.PokemonRemoteKeyEntity
import com.example.pokedexcompose.data.local.entities.PokemonSpeciesEntity
import com.example.pokedexcompose.data.local.relations.PokemonAndDetail
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    fun getPokemons(): PagingSource<Int, PokemonAndDetail>

    fun searchPokemonByName(name: String): Flow<PokemonAndDetail>

    suspend fun deleteAllPokemon()

    suspend fun saveAllPokemons(pokemonEntities: List<PokemonEntity>)

    suspend fun savePokemon(pokemonEntity: PokemonEntity)

    suspend fun saveAllRemoteKey(pokemonRemoteKeyEntities: List<PokemonRemoteKeyEntity>)

    suspend fun getPokemonRemoteKeyByName(pokemonName: String): PokemonRemoteKeyEntity

    suspend fun deleteAllRemoteKey()

    suspend fun saveRemoteKey(pokemonRemoteKeyEntity: PokemonRemoteKeyEntity)

    suspend fun saveAllPokemonDetail(pokemonDetails: List<PokemonDetail>)

    suspend fun saveAllPokemonSpecies(species: List<PokemonSpeciesEntity> )

    suspend fun saveAllEvolutionChain(evolutionChainEntity: List<EvolutionChainEntity>)

    suspend fun saveEvolutionChain(evolutionChainEntity: EvolutionChainEntity)

    fun searchEvolutionChainById(chainId: Int): EvolutionChainEntity?
}