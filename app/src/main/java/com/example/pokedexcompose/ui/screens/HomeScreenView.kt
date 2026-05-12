package com.example.pokedexcompose.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.example.pokedexcompose.ui.components.LoadingAnimation
import com.example.pokedexcompose.ui.components.PokemonItem
import com.example.pokedexcompose.ui.models.PokemonUI
import com.example.pokedexcompose.ui.viewmodels.HomeViewModel

@Composable
fun HomeScreen(viewModel: HomeViewModel, onClickPokemon: (PokemonUI) -> Unit = {}) {
    HomeScreenView(
        pokemonLazyPagingItems = viewModel.uiState.collectAsLazyPagingItems(),
        onClickPokemon
    )
}

@Composable
fun HomeScreenView(
    pokemonLazyPagingItems: LazyPagingItems<PokemonUI>,
    onClickPokemon: (PokemonUI) -> Unit = {}
) {

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            state = rememberLazyListState(),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            items(
                count = pokemonLazyPagingItems.itemCount,
                key = pokemonLazyPagingItems.itemKey { it.id }
            ) { index ->
                pokemonLazyPagingItems[index]?.let { pokemon ->
                    PokemonItem(
                        pokemon = pokemon,
                        onClickPokemon = onClickPokemon
                    )
                }
            }
        }

        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 8.dp, end = 8.dp)
        ) {

            when (pokemonLazyPagingItems.loadState.refresh) {
                is LoadState.Error -> Unit
                LoadState.Loading -> {
                    LoadingAnimation()
                }

                is LoadState.NotLoading -> Unit
            }

            when (pokemonLazyPagingItems.loadState.append) {
                is LoadState.Error -> Unit
                LoadState.Loading -> {
                    LoadingAnimation()
                }

                is LoadState.NotLoading -> Unit
            }
        }
    }
}
