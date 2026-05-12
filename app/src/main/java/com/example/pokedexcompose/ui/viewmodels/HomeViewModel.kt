package com.example.pokedexcompose.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.pokedexcompose.domain.usecase.GetPokemonListUseCase
import com.example.pokedexcompose.ui.mappers.toUI
import com.example.pokedexcompose.ui.models.PokemonUI
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class HomeViewModel(private val getPokemonListUseCase: GetPokemonListUseCase) : ViewModel() {

    private var _uiState: MutableStateFlow<PagingData<PokemonUI>> = MutableStateFlow(PagingData.from(emptyList()))
    val uiState get() = _uiState.asStateFlow()

    init {
        fetchPokemons()
    }

    private fun fetchPokemons() {
        viewModelScope.launch {
            getPokemonListUseCase()
                .map { pagingData -> pagingData.map { it.toUI() } }
                .cachedIn(viewModelScope)
                .collect {
                    _uiState.value = it
                }
        }
    }
}
