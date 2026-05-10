package com.example.pokedexcompose.data.repository

import com.example.pokedexcompose.data.local.relations.PokemonAndDetail
import kotlinx.coroutines.flow.Flow

interface DetailRepository {
    suspend fun searchPokemonByName(pokemonName: String): Flow<PokemonAndDetail>
}