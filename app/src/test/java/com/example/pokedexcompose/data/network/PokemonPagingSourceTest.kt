package com.example.pokedexcompose.data.network

import androidx.paging.PagingSource
import com.example.pokedexcompose.data.network.model.ListPokemonRemote
import com.example.pokedexcompose.data.network.model.PokemonRemote
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class PokemonPagingSourceTest {

    private val service = mockk<PokemonService>()
    private val pagingSource = PokemonPagingSource(service)

    @Test
    fun `load should return Page when success`() = runTest {
        // Given
        val pokemonList = listOf(PokemonRemote(name = "pikachu", url = "url/25/"))
        val response = ListPokemonRemote(
            count = 1,
            results = pokemonList,
            next = "https://pokeapi.co/api/v2/pokemon?offset=20&limit=20",
            previous = null
        )
        coEvery { service.getListPokemon(offset = 0) } returns response

        // When
        val result = pagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = 20,
                placeholdersEnabled = false
            )
        )

        // Then
        assertTrue(result is PagingSource.LoadResult.Page)
        val page = result as PagingSource.LoadResult.Page
        assertEquals(pokemonList, page.data)
        assertEquals(20, page.nextKey)
        assertEquals(null, page.prevKey)
    }

    @Test
    fun `load should return Page with prev and next keys when loading middle page`() = runTest {
        // Given
        val pokemonList = listOf(PokemonRemote(name = "ivysaur", url = "url/2/"))
        val response = ListPokemonRemote(
            count = 100,
            results = pokemonList,
            next = "https://pokeapi.co/api/v2/pokemon?offset=40&limit=20",
            previous = "https://pokeapi.co/api/v2/pokemon?offset=0&limit=20"
        )
        coEvery { service.getListPokemon(offset = 20) } returns response

        // When
        val result = pagingSource.load(
            PagingSource.LoadParams.Append(
                key = 20,
                loadSize = 20,
                placeholdersEnabled = false
            )
        )

        // Then
        assertTrue(result is PagingSource.LoadResult.Page)
        val page = result as PagingSource.LoadResult.Page
        assertEquals(40, page.nextKey)
        assertEquals(0, page.prevKey)
    }

    @Test
    fun `load should return Error when exception occurs`() = runTest {
        // Given
        val exception = RuntimeException("Network error")
        coEvery { service.getListPokemon(offset = any()) } throws exception

        // When
        val result = pagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = 20,
                placeholdersEnabled = false
            )
        )

        // Then
        assertTrue(result is PagingSource.LoadResult.Error)
        assertEquals(exception, (result as PagingSource.LoadResult.Error).throwable)
    }
}
