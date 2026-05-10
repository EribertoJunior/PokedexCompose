package com.example.pokedexcompose.data.local.converters

import androidx.room.TypeConverter
import com.example.pokedexcompose.data.local.entities.PokemonDetailStats
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ConverterPokemonDetailStats {
    @TypeConverter
    fun restoreList(typeSpeciesString: String): List<PokemonDetailStats> {
        return Gson().fromJson(
            typeSpeciesString,
            object : TypeToken<List<PokemonDetailStats>>() {}.type
        )
    }

    @TypeConverter
    fun saveListToListString(typeColoursEnums: List<PokemonDetailStats>): String {
        return Gson().toJson(typeColoursEnums)
    }
}