package com.example.pokedexcompose.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.pokedexcompose.data.local.entities.PokemonSpeciesEntity

@Dao
fun interface PokemonSpeciesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAllSpecie(species: List<PokemonSpeciesEntity>)
}