package com.example.pokedexcompose.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.pokedexcompose.data.dataSource.local.LocalDataSource
import com.example.pokedexcompose.data.local.relations.PokemonAndDetail
import kotlinx.coroutines.flow.Flow

class HomeRepositoryImpl(
    private val pokemonRemoteMediator: PokemonRemoteMediator,
    private val localDataSource: LocalDataSource
) : HomeRepository {

    @OptIn(ExperimentalPagingApi::class)
    override fun getPokemonList(): Flow<PagingData<PokemonAndDetail>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                prefetchDistance = PREFETCH_SIZE,
                maxSize = MAX_SIZE,
                enablePlaceholders = false
            ),
            remoteMediator = pokemonRemoteMediator,
            pagingSourceFactory = { localDataSource.getPokemons() }
        ).flow
    }

    private companion object {
        const val PAGE_SIZE = 40
        const val PREFETCH_SIZE = 10
        const val MAX_SIZE = PAGE_SIZE * 4
    }
}
