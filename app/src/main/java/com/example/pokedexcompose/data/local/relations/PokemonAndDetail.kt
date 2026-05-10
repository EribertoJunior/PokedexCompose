package com.example.pokedexcompose.data.local.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.pokedexcompose.data.local.entities.EvolutionChainEntity
import com.example.pokedexcompose.data.local.entities.EvolutionChainEntity.Companion.EVOLUTION_CHAIN_ID
import com.example.pokedexcompose.data.local.entities.PokemonEntity
import com.example.pokedexcompose.data.local.entities.PokemonEntity.Companion.POKEMON_ID
import com.example.pokedexcompose.data.local.entities.PokemonDetail
import com.example.pokedexcompose.data.local.entities.PokemonDetail.Companion.POKEMON_DETAIL_OWNER_ID
import com.example.pokedexcompose.data.local.entities.PokemonSpeciesEntity
import com.example.pokedexcompose.data.local.entities.PokemonSpeciesEntity.Companion.POKEMON_SPECIES_EVOLUTION_CHAIN_ID
import com.example.pokedexcompose.data.local.entities.PokemonSpeciesEntity.Companion.POKEMON_SPECIES_OWNER_ID

data class PokemonAndDetail(
    @Embedded val pokemonEntity: PokemonEntity,
    @Relation(
        parentColumn = POKEMON_ID,
        entityColumn = POKEMON_DETAIL_OWNER_ID
    )
    val pokemonDetail: PokemonDetail,
    @Relation(
        entity = PokemonSpeciesEntity::class,
        parentColumn = POKEMON_ID,
        entityColumn = POKEMON_SPECIES_OWNER_ID,
    )
    val specieAndEvolutionChain: SpecieAndEvolutionChain? = null
)

data class SpecieAndEvolutionChain(
    @Embedded val pokemonSpeciesEntity: PokemonSpeciesEntity,
    @Relation(
        parentColumn = POKEMON_SPECIES_EVOLUTION_CHAIN_ID,
        entityColumn = EVOLUTION_CHAIN_ID
    )
    val evolutionChainEntity: EvolutionChainEntity
)