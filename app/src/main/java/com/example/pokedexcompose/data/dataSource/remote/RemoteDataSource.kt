package com.example.pokedexcompose.data.dataSource.remote

import com.example.pokedexcompose.data.network.model.EvolutionChainRemote
import com.example.pokedexcompose.data.network.model.ListPokemonRemote
import com.example.pokedexcompose.data.network.model.PokemonDetailRemote
import com.example.pokedexcompose.data.network.model.SpeciesRemote

interface RemoteDataSource {
    suspend fun getListPokemon(
        limit: Int = 10,
        offset: Int
    ): ListPokemonRemote

    suspend fun getPokemonDetails(pokemonName: String) : PokemonDetailRemote

    suspend fun searchPokemonSpecie(pokemonSpecieUrl: String): SpeciesRemote?

    suspend fun searchEvolutionChan(evolutionChanUrl: String): EvolutionChainRemote
}