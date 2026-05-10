package com.example.pokedexcompose.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pokedexcompose.data.local.entities.PokemonRemoteKeyEntity

@Dao
interface PokemonRemoteKeyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAll(pokemonRemoteKeyEntity: List<PokemonRemoteKeyEntity>)

    @Query("Select * From PokemonRemoteKeyEntity Where pokemonName = :pokemonName")
    suspend fun getPokemonRemoteKeyFromName(pokemonName: String): PokemonRemoteKeyEntity

    @Query("Delete From PokemonRemoteKeyEntity")
    suspend fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(pokemonRemoteKeyEntity: PokemonRemoteKeyEntity)
}