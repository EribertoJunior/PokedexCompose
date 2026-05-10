package com.example.pokedexcompose.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedexcompose.data.local.entities.PokemonEntity
import com.example.pokedexcompose.data.local.entities.PokemonDetail
import com.example.pokedexcompose.data.local.relations.PokemonAndDetail
import com.example.pokedexcompose.data.repository.DetailRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class DetailsViewModel(private val detailRepository: DetailRepository) : ViewModel() {

    private var _uiState: MutableStateFlow<PokemonAndDetail> =
        MutableStateFlow(
            PokemonAndDetail(
                pokemonEntity = PokemonEntity(),
                pokemonDetail = PokemonDetail(),
                specieAndEvolutionChain = null
            )
        )
    val uiState get() = _uiState.asStateFlow()

    fun searchEvolutionChain(pokemonName: String) {
        viewModelScope.launch {
            detailRepository.searchPokemonByName(pokemonName).collectLatest {
                _uiState.value = it
            }
        }
    }
}