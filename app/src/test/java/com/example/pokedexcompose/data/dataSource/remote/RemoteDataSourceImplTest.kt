package com.example.pokedexcompose.data.dataSource.remote

import com.example.pokedexcompose.data.network.PokemonService
import com.example.pokedexcompose.data.network.model.EvolutionChainRemote
import com.example.pokedexcompose.data.network.model.ListPokemonRemote
import com.example.pokedexcompose.data.network.model.PokemonDetailRemote
import com.example.pokedexcompose.data.network.model.SpeciesRemote
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.spyk
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class RemoteDataSourceImplTest {

    private lateinit var remoteDataSourceImplMock: RemoteDataSourceImpl
    private lateinit var pokemonService: PokemonService

    @Before
    fun setUp() {
        pokemonService = mockk()
        remoteDataSourceImplMock = spyk(RemoteDataSourceImpl(pokemonService))
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `should return ListPokemonRemote when PokemonService returns a ListPokemonRemote`() {

        val listPokemonRemote = mockk<ListPokemonRemote>(relaxed = true)

        coEvery { pokemonService.getListPokemon(any(), any()) } answers { listPokemonRemote }

        runBlocking {
            val listPokemon = remoteDataSourceImplMock.getListPokemon(0, 0)
            assertEquals(listPokemonRemote, listPokemon)
        }
    }

    @Test
    fun `should return PokemonDetailRemote when PokemonService returns a PokemonDetailRemote`() {

        val pokemonDetailRemote = mockk<PokemonDetailRemote>(relaxed = true)

        coEvery { pokemonService.getPokemonDetails(any()) } answers { pokemonDetailRemote }

        runBlocking {
            val pokemonDetails = remoteDataSourceImplMock.getPokemonDetails("")
            assertEquals(pokemonDetailRemote, pokemonDetails)
        }
    }

    @Test
    fun `should return Species Remote when PokemonService returns a Species Remote`() {

        val speciesRemote = mockk<SpeciesRemote>(relaxed = true)

        coEvery { pokemonService.searchPokemonSpecie(any()) } answers { speciesRemote }

        runBlocking {
            val pokemonDetails = remoteDataSourceImplMock.searchPokemonSpecie("")
            assertEquals(speciesRemote, pokemonDetails)
        }
    }

    @Test
    fun `should return null when PokemonService does not return a SpeciesRemote`() {

        coEvery { pokemonService.searchPokemonSpecie(any()) } answers { null }

        runBlocking {
            val pokemonDetails = remoteDataSourceImplMock.searchPokemonSpecie("")
            assertEquals(null, pokemonDetails)
        }
    }

    @Test
    fun `must return EvolutionChainRemote when PokemonService returns an EvolutionChainRemote`() {

        val evolutionChainRemote = mockk<EvolutionChainRemote>(relaxed = true)

        coEvery { pokemonService.searchEvolutionChan(any()) } answers { evolutionChainRemote }

        runBlocking {
            val actual = remoteDataSourceImplMock.searchEvolutionChan("")
            assertEquals(evolutionChainRemote, actual)
        }
    }

}