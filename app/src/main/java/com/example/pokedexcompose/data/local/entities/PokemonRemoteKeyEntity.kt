package com.example.pokedexcompose.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PokemonRemoteKeyEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val pokemonName: String,
    val prevOffset: Int?,
    val nextOffset: Int?
)