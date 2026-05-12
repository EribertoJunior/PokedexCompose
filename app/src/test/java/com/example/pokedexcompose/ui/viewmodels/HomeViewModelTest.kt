package com.example.pokedexcompose.ui.viewmodels

import androidx.paging.PagingData
import com.example.pokedexcompose.domain.model.PokemonModel
import com.example.pokedexcompose.domain.usecase.GetPokemonListUseCase
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {

    private val useCase: GetPokemonListUseCase = mockk()
    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `fetchPokemons should collect PagingData from useCase`() {
        // Given
        val pagingData = PagingData.empty<PokemonModel>()
        every { useCase() } returns flowOf(pagingData)

        // When
        HomeViewModel(useCase)

        // Then
        verify { useCase() }
    }
}
