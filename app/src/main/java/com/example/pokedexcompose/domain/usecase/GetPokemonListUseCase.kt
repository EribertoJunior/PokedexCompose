package com.example.pokedexcompose.domain.usecase

import androidx.paging.PagingData
import androidx.paging.map
import com.example.pokedexcompose.data.mapper.toDomain
import com.example.pokedexcompose.data.repository.HomeRepository
import com.example.pokedexcompose.domain.model.PokemonModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetPokemonListUseCase(private val repository: HomeRepository) {
    operator fun invoke(): Flow<PagingData<PokemonModel>> {
        return repository.getPokemonList().map { pagingData ->
            pagingData.map { it.toDomain() }
        }
    }
}
