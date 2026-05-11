package com.example.pokedexcompose.ui.viewmodels

import com.example.pokedexcompose.data.local.entities.PokemonEntity
import com.example.pokedexcompose.data.local.entities.PokemonDetail
import com.example.pokedexcompose.data.local.relations.PokemonAndDetail
import com.example.pokedexcompose.data.repository.DetailRepository
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

    private val repository = mockk<DetailRepository>()
    private lateinit var viewModel: DetailsViewModel
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        viewModel = DetailsViewModel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `searchEvolutionChain should update uiState with data from repository`() = runTest {
        // Given
        val pokemonName = "Bulbasaur"
        val expectedPokemon = PokemonAndDetail(
            pokemonEntity = PokemonEntity(name = pokemonName),
            pokemonDetail = PokemonDetail(),
            specieAndEvolutionChain = null
        )
        
        coEvery { repository.searchPokemonByName(pokemonName) } returns flowOf(expectedPokemon)

        // When
        viewModel.searchEvolutionChain(pokemonName)
        advanceUntilIdle()

        // Then
        assertEquals(expectedPokemon, viewModel.uiState.value)
    }
}
