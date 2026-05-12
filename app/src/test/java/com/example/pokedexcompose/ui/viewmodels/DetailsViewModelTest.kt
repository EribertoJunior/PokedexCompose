package com.example.pokedexcompose.ui.viewmodels

import com.example.pokedexcompose.domain.model.PokemonModel
import com.example.pokedexcompose.domain.usecase.GetPokemonDetailsUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class DetailsViewModelTest {

    private val useCase = mockk<GetPokemonDetailsUseCase>()
    private lateinit var viewModel: DetailsViewModel
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        viewModel = DetailsViewModel(useCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `searchEvolutionChain should update uiState with data from useCase`() = runTest {
        // Given
        val pokemonName = "Bulbasaur"
        val expectedPokemon = PokemonModel(
            id = 1,
            name = pokemonName,
            imageUrl = "url"
        )
        
        coEvery { useCase(pokemonName) } returns flowOf(expectedPokemon)

        // When
        viewModel.searchEvolutionChain(pokemonName)
        advanceUntilIdle()

        // Then
        assertEquals(pokemonName, viewModel.uiState.value.name)
        assertEquals("#001", viewModel.uiState.value.idFormatted)
    }
}
