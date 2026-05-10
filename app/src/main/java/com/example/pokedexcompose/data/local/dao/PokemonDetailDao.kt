package com.example.pokedexcompose.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.pokedexcompose.data.local.entities.PokemonDetail

@Dao
interface PokemonDetailDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAll(pokemonDetails: List<PokemonDetail>)
}