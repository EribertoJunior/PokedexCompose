package com.example.pokedexcompose.data.repository

import androidx.paging.PagingData
import com.example.pokedexcompose.data.model.local.PokemonAndDetail
import kotlinx.coroutines.flow.Flow

fun interface HomeRepository {
    fun getPokemonList(): Flow<PagingData<PokemonAndDetail>>
}