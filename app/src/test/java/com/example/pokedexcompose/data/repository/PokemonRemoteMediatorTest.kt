package com.example.pokedexcompose.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingConfig
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.example.pokedexcompose.data.dataSource.local.LocalDataSource
import com.example.pokedexcompose.data.dataSource.remote.RemoteDataSource
import com.example.pokedexcompose.data.local.relations.PokemonAndDetail
import com.example.pokedexcompose.data.network.model.ListPokemonRemote
import com.example.pokedexcompose.data.network.model.PokemonDetailRemote
import com.example.pokedexcompose.data.network.model.PokemonRemote
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Test

@ExperimentalPagingApi
internal class PokemonRemoteMediatorTest {

    private val localDataSourceMock = mockk<LocalDataSource>()
    private val remoteDataSourceMock = mockk<RemoteDataSource>()
    private var response = mockk<ListPokemonRemote>()
    private val pokemonDetailMock = mockk<PokemonDetailRemote>(relaxed = true)
    private val remoteMediatorSpy = spyk(
        PokemonRemoteMediator(
            localDataSource = localDataSourceMock,
            remoteDataSource = remoteDataSourceMock,
        )
    )

    @After
    fun after() {
        response = mockk()
    }

    @Test
    fun `refresh Load Returns SuccessResult When More Data Is Present`() {
        val pokemonRemote = PokemonRemote(name = "bulbasaur", url = "url")
        coEvery { response.results } returns listOf(pokemonRemote)
        coEvery { response.previous } returns null
        coEvery { response.next } returns "https://pokeapi.co/api/v2/pokemon?offset=40&limit=40"
        
        // Mock getOffsetParameter to avoid android.net.Uri dependency in Unit Tests
        every { remoteMediatorSpy.getOffsetParameter(any()) } answers {
            val url = it.invocation.args[0] as String?
            if (url != null && url.contains("offset=40")) 40 else null
        }
        
        coEvery {
            remoteDataSourceMock.getListPokemon(
                limit = 40,
                offset = 0
            )
        } returns response
        
        coEvery { remoteDataSourceMock.getPokemonDetails(any()) } returns pokemonDetailMock
        coEvery { remoteDataSourceMock.searchPokemonSpecie(any()) } returns null

        coEvery { localDataSourceMock.saveAllPokemons(any()) } answers {}
        coEvery { localDataSourceMock.saveAllRemoteKey(any()) } answers {}
        coEvery { localDataSourceMock.saveAllPokemonDetail(any()) } answers {}
        coEvery { localDataSourceMock.saveAllPokemonSpecies(any()) } answers {}
        coEvery { localDataSourceMock.getPokemonRemoteKeyByName(any()) } returns mockk(relaxed = true)

        val result = runBlocking {
            remoteMediatorSpy.load(LoadType.REFRESH, getPagingState())
        }

        assert(result is RemoteMediator.MediatorResult.Success)
        assert((result as RemoteMediator.MediatorResult.Success).endOfPaginationReached.not())
    }

    @Test
    fun `refresh Load Success And End OfPagination When No More Data`() {

        coEvery { response.results } returns listOf()
        coEvery { response.previous } returns null
        coEvery { response.next } returns null
        
        every { remoteMediatorSpy.getOffsetParameter(any()) } returns null

        coEvery {
            remoteDataSourceMock.getListPokemon(
                limit = 40,
                offset = 0
            )
        } returns response
        
        coEvery { localDataSourceMock.getPokemonRemoteKeyByName(any()) } returns mockk(relaxed = true)

        val result = runBlocking {
            remoteMediatorSpy.load(LoadType.REFRESH, getPagingState())
        }

        assert(result is RemoteMediator.MediatorResult.Success)
        assert((result as RemoteMediator.MediatorResult.Success).endOfPaginationReached)
    }

    @Test
    fun `refresh Load Returns Error Result When Error Occurs`() {

        coEvery { remoteDataSourceMock.getListPokemon(limit = 40, offset = 0) }.throws(
            RuntimeException()
        )
        
        coEvery { localDataSourceMock.getPokemonRemoteKeyByName(any()) } returns mockk(relaxed = true)

        val result = runBlocking {
            remoteMediatorSpy.load(LoadType.REFRESH, getPagingState())
        }

        assert(result is RemoteMediator.MediatorResult.Error)
    }

    private fun getPagingState() = PagingState<Int, PokemonAndDetail>(
        pages = listOf(),
        anchorPosition = null,
        config = PagingConfig(40),
        leadingPlaceholderCount = 10
    )
}
