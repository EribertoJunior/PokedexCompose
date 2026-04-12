package com.example.pokedexcompose.data.dataBase.local.entities

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity
data class Pokemon(
    @PrimaryKey val pokemonId: Int = 0,
    val name: String = "",
    val imageUrl: String? = null
) {
    @get:Ignore
    val idFormatted: String
        get() {
            return if (pokemonId < 10) {
                "#00$pokemonId"
            } else if (pokemonId in 10..99) {
                "#0$pokemonId"
            } else {
                "#$pokemonId"
            }
        }

    companion object {
        const val POKEMON_ID = "pokemonId"
    }
}
