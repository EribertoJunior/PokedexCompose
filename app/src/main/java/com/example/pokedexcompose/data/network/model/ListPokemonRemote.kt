package com.example.pokedexcompose.data.network.model

data class ListPokemonRemote(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<PokemonRemote>
)
