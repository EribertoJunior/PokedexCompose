package com.example.pokedexcompose.data.model.local

import androidx.room.Embedded
import androidx.room.Relation
import com.example.pokedexcompose.data.dataBase.local.entities.EvolutionChain
import com.example.pokedexcompose.data.dataBase.local.entities.EvolutionChain.Companion.EVOLUTION_CHAIN_ID
import com.example.pokedexcompose.data.dataBase.local.entities.Pokemon
import com.example.pokedexcompose.data.dataBase.local.entities.Pokemon.Companion.POKEMON_ID
import com.example.pokedexcompose.data.dataBase.local.entities.PokemonDetail
import com.example.pokedexcompose.data.dataBase.local.entities.PokemonDetail.Companion.POKEMON_DETAIL_OWNER_ID
import com.example.pokedexcompose.data.dataBase.local.entities.PokemonSpecies
import com.example.pokedexcompose.data.dataBase.local.entities.PokemonSpecies.Companion.POKEMON_SPECIES_EVOLUTION_CHAIN_ID
import com.example.pokedexcompose.data.dataBase.local.entities.PokemonSpecies.Companion.POKEMON_SPECIES_OWNER_ID

data class PokemonAndDetail(
    @Embedded val pokemon: Pokemon,
    @Relation(
        parentColumn = POKEMON_ID,
        entityColumn = POKEMON_DETAIL_OWNER_ID
    )
    val pokemonDetail: PokemonDetail,
    @Relation(
        entity = PokemonSpecies::class,
        parentColumn = POKEMON_ID,
        entityColumn = POKEMON_SPECIES_OWNER_ID,
    )
    val specieAndEvolutionChain: SpecieAndEvolutionChain? = null
)

data class SpecieAndEvolutionChain(
    @Embedded val pokemonSpecies: PokemonSpecies,
    @Relation(
        parentColumn = POKEMON_SPECIES_EVOLUTION_CHAIN_ID,
        entityColumn = EVOLUTION_CHAIN_ID
    )
    val evolutionChain: EvolutionChain
)