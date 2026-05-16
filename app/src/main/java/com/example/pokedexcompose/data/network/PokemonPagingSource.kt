package com.example.pokedexcompose.data.network

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.pokedexcompose.data.network.model.PokemonRemote
import com.example.pokedexcompose.extensions.getOffsetFromUrl
import java.lang.Exception

class PokemonPagingSource(private val pokeService: PokemonService) : PagingSource<Int, PokemonRemote>() {
    override fun getRefreshKey(state: PagingState<Int, PokemonRemote>): Int? = state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokemonRemote> {
        return try {

            val offset = params.key ?: PRIMEIRO_DESLOCAMENTO_OFFSET
            val response = pokeService.getListPokemon(offset = offset)
            val nextOffset = response.next?.getOffsetFromUrl()
            val prevOffset = response.previous?.getOffsetFromUrl()


            LoadResult.Page(data = response.results, nextKey = nextOffset, prevKey = prevOffset)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    companion object {
        const val PRIMEIRO_DESLOCAMENTO_OFFSET = 0
    }
}