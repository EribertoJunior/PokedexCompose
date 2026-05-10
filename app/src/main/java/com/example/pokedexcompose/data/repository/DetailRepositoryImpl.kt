package com.example.pokedexcompose.data.repository

import com.example.pokedexcompose.data.dataSource.local.LocalDataSource
import com.example.pokedexcompose.data.local.relations.PokemonAndDetail
import kotlinx.coroutines.flow.Flow

class DetailRepositoryImpl(
    private val localDataSource: LocalDataSource
) : DetailRepository {

    override suspend fun searchPokemonByName(pokemonName: String): Flow<PokemonAndDetail> {
        return localDataSource.searchPokemonByName(pokemonName)
    }
}