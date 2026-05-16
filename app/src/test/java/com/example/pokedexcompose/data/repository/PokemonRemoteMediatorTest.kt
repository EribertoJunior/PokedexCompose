package com.example.pokedexcompose.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.example.pokedexcompose.data.dataSource.local.LocalDataSource
import com.example.pokedexcompose.data.dataSource.remote.RemoteDataSource
import com.example.pokedexcompose.data.local.relations.PokemonAndDetail
import com.example.pokedexcompose.data.network.model.*
import io.mockk.*
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Test

@ExperimentalPagingApi
internal class PokemonRemoteMediatorTest {

    private val localDataSourceMock = mockk<LocalDataSource>(relaxed = true)
    private val remoteDataSourceMock = mockk<RemoteDataSource>(relaxed = true)
    private var response = mockk<ListPokemonRemote>(relaxed = true)
    private val remoteMediator = PokemonRemoteMediator(
        localDataSource = localDataSourceMock,
        remoteDataSource = remoteDataSourceMock
    )

    @After
    fun after() {
        clearAllMocks()
    }

    @Test
    fun `refresh Load Returns SuccessResult When More Data Is Present`() = runBlocking {
        val pokemonRemote = PokemonRemote(name = "bulbasaur", url = "url")
        coEvery { response.results } returns listOf(pokemonRemote)
        coEvery { response.previous } returns null
        coEvery { response.next } returns "https://pokeapi.co/api/v2/pokemon?offset=40&limit=40"
        
        coEvery {
            remoteDataSourceMock.getListPokemon(limit = 40, offset = 0)
        } returns response
        
        val pokemonDetail = createPokemonDetailRemote("bulbasaur")
        coEvery { remoteDataSourceMock.getPokemonDetails(any()) } returns pokemonDetail
        coEvery { remoteDataSourceMock.searchPokemonSpecie(any()) } returns null

        val result = remoteMediator.load(LoadType.REFRESH, getPagingState())

        assert(result is RemoteMediator.MediatorResult.Success)
        assert((result as RemoteMediator.MediatorResult.Success).endOfPaginationReached.not())
    }

    @Test
    fun `refresh Load Success And End OfPagination When No More Data`() = runBlocking {
        coEvery { response.results } returns listOf()
        coEvery {
            remoteDataSourceMock.getListPokemon(limit = 40, offset = 0)
        } returns response
        
        val result = remoteMediator.load(LoadType.REFRESH, getPagingState())

        assert(result is RemoteMediator.MediatorResult.Success)
        assert((result as RemoteMediator.MediatorResult.Success).endOfPaginationReached)
    }

    @Test
    fun `append Load Returns SuccessResult When More Data Is Present`() = runBlocking {
        val pokemonRemote = PokemonRemote(name = "ivysaur", url = "url")
        coEvery { response.results } returns listOf(pokemonRemote)
        coEvery { response.next } returns "next_url"
        coEvery { response.previous } returns "prev_url"
        coEvery { response.count } returns 100

        coEvery {
            localDataSourceMock.getPokemonRemoteKeyByName(any())
        } returns mockk(relaxed = true) {
            every { nextOffset } returns 40
        }

        coEvery {
            remoteDataSourceMock.getListPokemon(limit = 40, offset = 40)
        } returns response
        
        coEvery { remoteDataSourceMock.getPokemonDetails(any()) } returns createPokemonDetailRemote("ivysaur")

        val result = remoteMediator.load(LoadType.APPEND, getPagingState())

        assert(result is RemoteMediator.MediatorResult.Success)
        assert((result as RemoteMediator.MediatorResult.Success).endOfPaginationReached.not())
    }

    @Test
    fun `prepend Load Returns SuccessResult and EndOfPaginationReached`() = runBlocking {
        val pokemon = mockk<PokemonAndDetail>(relaxed = true)
        val page = PagingSource.LoadResult.Page(
            data = listOf(pokemon),
            prevKey = null,
            nextKey = 40
        )
        val state = PagingState<Int, PokemonAndDetail>(
            pages = listOf(page),
            anchorPosition = 0,
            config = PagingConfig(40),
            leadingPlaceholderCount = 10
        )

        coEvery {
            localDataSourceMock.getPokemonRemoteKeyByName(any())
        } returns mockk(relaxed = true) {
            every { prevOffset } returns null
        }

        val result = remoteMediator.load(LoadType.PREPEND, state)

        assert(result is RemoteMediator.MediatorResult.Success)
        assert((result as RemoteMediator.MediatorResult.Success).endOfPaginationReached)
    }

    @Test
    fun `refresh Load should also fetch species and evolution chain`() = runBlocking {
        val pokemonRemote = PokemonRemote(name = "bulbasaur", url = "url")
        val pokemonDetail = createPokemonDetailRemote("bulbasaur").copy(
            species = PokemonDetailRemoteSpecies(name = "bulbasaur", url = "species_url")
        )
        val speciesRemote = mockk<SpeciesRemote>(relaxed = true) {
            every { evolutionChainAddressRemote } returns EvolutionChainAddressRemote(url = "evolution_url")
        }
        val evolutionChainRemote = mockk<EvolutionChainRemote>(relaxed = true)

        coEvery { response.results } returns listOf(pokemonRemote)
        coEvery { remoteDataSourceMock.getListPokemon(any(), any()) } returns response
        coEvery { remoteDataSourceMock.getPokemonDetails(any()) } returns pokemonDetail
        coEvery { remoteDataSourceMock.searchPokemonSpecie("species_url") } returns speciesRemote
        coEvery { remoteDataSourceMock.searchEvolutionChan("evolution_url") } returns evolutionChainRemote
        coEvery { localDataSourceMock.searchEvolutionChainById(any()) } returns null

        val result = remoteMediator.load(LoadType.REFRESH, getPagingState())

        assert(result is RemoteMediator.MediatorResult.Success)
        coVerify { remoteDataSourceMock.searchEvolutionChan("evolution_url") }
        coVerify { localDataSourceMock.saveEvolutionChain(any()) }
    }

    @Test
    fun `refresh Load Returns Error Result When Error Occurs`() = runBlocking {
        coEvery { remoteDataSourceMock.getListPokemon(any(), any()) }.throws(RuntimeException())
        
        val result = remoteMediator.load(LoadType.REFRESH, getPagingState())

        assert(result is RemoteMediator.MediatorResult.Error)
    }

    private fun getPagingState() = PagingState<Int, PokemonAndDetail>(
        pages = listOf(),
        anchorPosition = null,
        config = PagingConfig(40),
        leadingPlaceholderCount = 10
    )

    private fun createPokemonDetailRemote(name: String) = PokemonDetailRemote(
        id = 1,
        name = name,
        weight = 10,
        height = 10,
        types = listOf(DataTypesRemote(1, TypeRemote("grass"))),
        sprites = SpritesRemote(OtherRemote(OfficialArtworkRemote("url"), HomeRemote("url"))),
        species = null,
        stats = emptyList()
    )
}
