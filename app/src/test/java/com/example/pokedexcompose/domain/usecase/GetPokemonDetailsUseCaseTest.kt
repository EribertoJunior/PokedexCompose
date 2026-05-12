package com.example.pokedexcompose.domain.usecase

import com.example.pokedexcompose.data.repository.DetailRepository
import com.example.pokedexcompose.data.local.relations.PokemonAndDetail
import com.example.pokedexcompose.data.local.entities.PokemonEntity
import com.example.pokedexcompose.data.local.entities.PokemonDetail
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class GetPokemonDetailsUseCaseTest {

    private val repository = mockk<DetailRepository>()
    private val useCase = GetPokemonDetailsUseCase(repository)

    @Test
    fun `invoke should return mapped pokemon from repository`() = runTest {
        // Given
        val pokemonName = "Charizard"
        val pokemonAndDetail = PokemonAndDetail(
            pokemonEntity = PokemonEntity(name = pokemonName),
            pokemonDetail = PokemonDetail(colorTypeList = emptyList(), stats = emptyList())
        )
        coEvery { repository.searchPokemonByName(pokemonName) } returns flowOf(pokemonAndDetail)

        // When
        val result = useCase(pokemonName).first()

        // Then
        assertEquals(pokemonName, result.name)
    }
}
