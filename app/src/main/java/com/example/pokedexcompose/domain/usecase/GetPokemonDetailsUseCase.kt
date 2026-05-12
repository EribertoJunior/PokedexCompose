package com.example.pokedexcompose.domain.usecase

import com.example.pokedexcompose.data.mapper.toDomain
import com.example.pokedexcompose.data.repository.DetailRepository
import com.example.pokedexcompose.domain.model.PokemonModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetPokemonDetailsUseCase(private val repository: DetailRepository) {
    suspend operator fun invoke(name: String): Flow<PokemonModel> {
        return repository.searchPokemonByName(name).map { it.toDomain() }
    }
}
