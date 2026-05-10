package com.example.pokedexcompose.data.local.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PokemonSpeciesEntity(
    @PrimaryKey val pokemonSpeciesId: Int = 0,
    val pokemonOwnerId: Int = 0,
    val pokemonSpeciesEvolutionChainId: Int = 0,
    @Embedded val evolutionChainAddress: EvolutionChainAddress,
    @Embedded val flavorTextEntries: FlavorTextEntries
){
    companion object {
        const val POKEMON_SPECIES_ID = "pokemonSpeciesId"
        const val POKEMON_SPECIES_OWNER_ID = "pokemonOwnerId"
        const val POKEMON_SPECIES_EVOLUTION_CHAIN_ID = "pokemonSpeciesEvolutionChainId"
    }
}

data class EvolutionChainAddress(
    val url: String?
)

data class FlavorTextEntries(
    val flavorText: String,
    @Embedded(prefix = "version_") val version: Version,
    @Embedded(prefix = "language_") val language: Language
)

data class Language(
    val name: String
)

data class Version(
    val name: String
)
