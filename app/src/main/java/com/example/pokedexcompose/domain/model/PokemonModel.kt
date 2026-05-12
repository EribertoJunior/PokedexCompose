package com.example.pokedexcompose.domain.model

data class PokemonModel(
    val id: Int = 0,
    val name: String = "",
    val imageUrl: String? = null,
    val types: List<PokemonTypeModel> = emptyList(),
    val weight: Int = 0,
    val height: Int = 0,
    val stats: List<PokemonStatModel> = emptyList(),
    val description: String = "",
    val evolutionChain: List<PokemonEvolutionModel> = emptyList()
)

data class PokemonStatModel(
    val name: String,
    val baseStat: Int
)

data class PokemonTypeModel(
    val name: String,
    val color: String
)

data class PokemonEvolutionModel(
    val name: String,
    val imageUrl: String
)
