package com.example.pokedexcompose.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedexcompose.domain.usecase.GetPokemonDetailsUseCase
import com.example.pokedexcompose.ui.mappers.toUI
import com.example.pokedexcompose.ui.models.PokemonUI
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class DetailsViewModel(private val getPokemonDetailsUseCase: GetPokemonDetailsUseCase) : ViewModel() {

    private var _uiState: MutableStateFlow<PokemonUI> = MutableStateFlow(PokemonUI())
    val uiState get() = _uiState.asStateFlow()

    fun searchEvolutionChain(pokemonName: String) {
        viewModelScope.launch {
            getPokemonDetailsUseCase(pokemonName)
                .map { it.toUI() }
                .collectLatest {
                    _uiState.value = it
                }
        }
    }
}
