package com.example.pokedexcompose.domain.usecase

import androidx.paging.PagingData
import com.example.pokedexcompose.data.repository.HomeRepository
import com.example.pokedexcompose.data.local.relations.PokemonAndDetail
import com.example.pokedexcompose.data.local.entities.PokemonEntity
import com.example.pokedexcompose.data.local.entities.PokemonDetail
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertNotNull
import org.junit.Test

class GetPokemonListUseCaseTest {

    private val repository = mockk<HomeRepository>()
    private val useCase = GetPokemonListUseCase(repository)

    @Test
    fun `invoke should return paging data from repository`() = runTest {
        // Given
        val pokemonAndDetail = PokemonAndDetail(
            pokemonEntity = PokemonEntity(name = "Pikachu"),
            pokemonDetail = PokemonDetail(colorTypeList = emptyList(), stats = emptyList())
        )
        val pagingData = PagingData.from(listOf(pokemonAndDetail))
        every { repository.getPokemonList() } returns flowOf(pagingData)

        // When
        val result = useCase().first()

        // Then
        assertNotNull(result)
    }
}
