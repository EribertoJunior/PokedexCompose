package com.example.pokedexcompose.data.network

import com.example.pokedexcompose.data.network.model.EvolutionChainRemote
import com.example.pokedexcompose.data.network.model.ListPokemonRemote
import com.example.pokedexcompose.data.network.model.PokemonDetailRemote
import com.example.pokedexcompose.data.network.model.SpeciesRemote
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface PokemonService {
    @GET("pokemon")
    suspend fun getListPokemon(
        @Query("limit") limit: Int = 10,
        @Query("offset") offset: Int
    ): ListPokemonRemote

    @GET("pokemon/{name}")
    suspend fun getPokemonDetails(@Path("name") pokemonName: String) : PokemonDetailRemote

    @GET
    suspend fun searchPokemonSpecie(@Url pokemonSpecieUrl: String): SpeciesRemote?

    @GET
    suspend fun searchEvolutionChan(@Url evolutionChanUrl: String): EvolutionChainRemote
}