package com.example.pokedexcompose.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.pokedexcompose.data.dataBase.local.PokemonDao
import com.example.pokedexcompose.data.model.local.PokemonAndDetail
import kotlinx.coroutines.flow.Flow

class HomeRepositoryImpl(
    private val pokemonRemoteMediator: PokemonRemoteMediator,
    private val pokemonDao: PokemonDao
) : HomeRepository {

    @ExperimentalPagingApi
    override fun getPokemonList(): Flow<PagingData<PokemonAndDetail>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                maxSize = MAX_SIZE,
                initialLoadSize = PAGE_SIZE + PREFETCH_SIZE,
                prefetchDistance = PREFETCH_SIZE,
                enablePlaceholders = false
            ),
            remoteMediator = pokemonRemoteMediator,
            pagingSourceFactory = { pokemonDao.getPokemons() }
        ).flow
    }

    private companion object {
        const val PREFETCH_SIZE = 50
        const val PAGE_SIZE = 100
        const val MAX_SIZE = PAGE_SIZE + (PREFETCH_SIZE * 2)
    }
}