package com.example.pokedexcompose.ui.models

data class PokemonUI(
    val id: Int = 0,
    val name: String = "",
    val imageUrl: String? = null,
    val types: List<PokemonTypeUI> = emptyList(),
    val weight: String = "",
    val height: String = "",
    val idFormatted: String = "",
    val description: String = "",
    val stats: List<PokemonStatUI> = emptyList(),
    val evolutionChain: List<PokemonEvolutionUI> = emptyList()
)

data class PokemonStatUI(
    val name: String,
    val value: Int,
    val progress: Float
)

data class PokemonEvolutionUI(
    val name: String,
    val imageUrl: String
)
