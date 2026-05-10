package com.example.pokedexcompose.data.dataSource.local

import com.example.pokedexcompose.data.local.dao.EvolutionChainDao
import com.example.pokedexcompose.data.local.dao.PokemonDao
import com.example.pokedexcompose.data.local.dao.PokemonDetailDao
import com.example.pokedexcompose.data.local.dao.PokemonRemoteKeyDao
import com.example.pokedexcompose.data.local.dao.PokemonSpeciesDao
import com.example.pokedexcompose.data.local.entities.EvolutionChainEntity
import com.example.pokedexcompose.data.local.entities.PokemonRemoteKeyEntity
import com.example.pokedexcompose.data.local.relations.PokemonAndDetail
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.spyk
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class LocalDataSourceImplTest {

    private lateinit var pokemonDao: PokemonDao
    private lateinit var pokemonRemoteKeyDao: PokemonRemoteKeyDao
    private lateinit var pokemonDetailDao: PokemonDetailDao
    private lateinit var pokemonSpeciesDao: PokemonSpeciesDao
    private lateinit var evolutionChainDao: EvolutionChainDao
    private lateinit var localDataSourceImpl: LocalDataSourceImpl

    @Before
    fun setUp() {
        pokemonDao = mockk()
        pokemonRemoteKeyDao = mockk()
        pokemonDetailDao = mockk()
        pokemonSpeciesDao = mockk()
        evolutionChainDao = mockk()
        localDataSourceImpl = spyk(
            LocalDataSourceImpl(
                pokemonDao = pokemonDao,
                pokemonSpeciesDao = pokemonSpeciesDao,
                pokemonDetailDao = pokemonDetailDao,
                evolutionChainDao = evolutionChainDao,
                pokemonRemoteKeyDao = pokemonRemoteKeyDao
            )
        )
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `must return a Flow of PokemonAndDetail when pokemonDao returns a Flow of PokemonAndDetail`() {
        val pokemonAndDetail = flow<PokemonAndDetail> { emit(mockk(relaxed = true)) }

        coEvery { pokemonDao.searchPokemonByName(any()) } answers { pokemonAndDetail }

        runBlocking {
            val pokemonAndDetailFlow = localDataSourceImpl.searchPokemonByName("")
            assertEquals(pokemonAndDetail, pokemonAndDetailFlow)
        }
    }

    @Test
    fun `must delete all pokemons`() {

        coEvery { pokemonDao.deleteAll() } answers {}

        runBlocking {
            localDataSourceImpl.deleteAllPokemon()
            coVerify { pokemonDao.deleteAll() }
        }
    }

    @Test
    fun `must save a list of pokemons`() {

        coEvery { pokemonDao.saveAll(any()) } answers {}

        runBlocking {
            localDataSourceImpl.saveAllPokemons(mockk(relaxed = true))
            coVerify { pokemonDao.saveAll(any()) }
        }
    }

    @Test
    fun `must save a pokemon`() {

        coEvery { pokemonDao.save(any()) } answers {}

        runBlocking {
            localDataSourceImpl.savePokemon(mockk(relaxed = true))
            coVerify { pokemonDao.save(any()) }
        }
    }

    @Test
    fun `must save a list of RemoteKey`() {

        coEvery { pokemonRemoteKeyDao.saveAll(any()) } answers {}

        runBlocking {
            localDataSourceImpl.saveAllRemoteKey(mockk(relaxed = true))
            coVerify { pokemonRemoteKeyDao.saveAll(any()) }
        }
    }

    @Test
    fun `should return a PokemonRemoteKey when pokemonRemoteKeyDao returns a PokemonRemoteKey`() {

        val pokemonRemoteKeyEntity = mockk<PokemonRemoteKeyEntity>(relaxed = true)

        coEvery { pokemonRemoteKeyDao.getPokemonRemoteKeyFromName(any()) } answers { pokemonRemoteKeyEntity }

        runBlocking {
            val pokemonRemoteKeyByName =
                localDataSourceImpl.getPokemonRemoteKeyByName("")

            assertEquals(pokemonRemoteKeyEntity, pokemonRemoteKeyByName)
        }
    }

    @Test
    fun `must delete all RemoteKey`() {

        coEvery { pokemonRemoteKeyDao.deleteAll() } answers {}

        runBlocking {
            localDataSourceImpl.deleteAllRemoteKey()
            coVerify { pokemonRemoteKeyDao.deleteAll() }
        }
    }

    @Test
    fun `must save a RemoteKey`() {

        coEvery { pokemonRemoteKeyDao.save(any()) } answers {}

        runBlocking {
            localDataSourceImpl.saveRemoteKey(mockk(relaxed = true))
            coVerify { pokemonRemoteKeyDao.save(any()) }
        }
    }

    @Test
    fun `must save a list of PokemonDetail`() {

        coEvery { pokemonDetailDao.saveAll(any()) } answers {}

        runBlocking {
            localDataSourceImpl.saveAllPokemonDetail(mockk(relaxed = true))
            coVerify { pokemonDetailDao.saveAll(any()) }
        }
    }

    @Test
    fun `must save a list of PokemonSpecies`() {

        coEvery { pokemonSpeciesDao.saveAllSpecie(any()) } answers {}

        runBlocking {
            localDataSourceImpl.saveAllPokemonSpecies(mockk(relaxed = true))
            coVerify { pokemonSpeciesDao.saveAllSpecie(any()) }
        }
    }

    @Test
    fun `should save a list of saveAllEvolutionChain`() {

        coEvery { evolutionChainDao.saveAll(any()) } answers {}

        runBlocking {
            localDataSourceImpl.saveAllEvolutionChain(mockk(relaxed = true))
            coVerify { evolutionChainDao.saveAll(any()) }
        }
    }

    @Test
    fun `must save a saveAllEvolutionChain`() {

        coEvery { evolutionChainDao.save(any()) } answers {}

        runBlocking {
            localDataSourceImpl.saveEvolutionChain(mockk(relaxed = true))
            coVerify { evolutionChainDao.save(any()) }
        }
    }

    @Test
    fun `should return an Evolution Chain when pokemonRemoteKeyDao returns an Evolution Chain`() {

        val pokemonRemoteKey = mockk<EvolutionChainEntity>(relaxed = true)

        coEvery { evolutionChainDao.searchEvolutionChainById(any()) } answers { pokemonRemoteKey }

        runBlocking {
            val pokemonRemoteKeyByName = evolutionChainDao.searchEvolutionChainById(0)

            assertEquals(pokemonRemoteKey, pokemonRemoteKeyByName)
        }
    }
}