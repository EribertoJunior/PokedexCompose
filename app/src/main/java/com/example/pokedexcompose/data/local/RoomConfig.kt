package com.example.pokedexcompose.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.pokedexcompose.data.local.converters.ConverterPokemonDetailStats
import com.example.pokedexcompose.data.local.converters.ConverterSpecieToEvolution
import com.example.pokedexcompose.data.local.converters.ConverterTypeColoursEnum
import com.example.pokedexcompose.data.local.dao.EvolutionChainDao
import com.example.pokedexcompose.data.local.dao.PokemonDao
import com.example.pokedexcompose.data.local.dao.PokemonDetailDao
import com.example.pokedexcompose.data.local.dao.PokemonRemoteKeyDao
import com.example.pokedexcompose.data.local.dao.PokemonSpeciesDao
import com.example.pokedexcompose.data.local.entities.EvolutionChainEntity
import com.example.pokedexcompose.data.local.entities.PokemonEntity
import com.example.pokedexcompose.data.local.entities.PokemonDetail
import com.example.pokedexcompose.data.local.entities.PokemonRemoteKeyEntity
import com.example.pokedexcompose.data.local.entities.PokemonSpeciesEntity

@Database(
    entities = [PokemonEntity::class, EvolutionChainEntity::class, PokemonDetail::class, PokemonRemoteKeyEntity::class, PokemonSpeciesEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(
    ConverterTypeColoursEnum::class,
    ConverterPokemonDetailStats::class,
    ConverterSpecieToEvolution::class
)
abstract class RoomConfig : RoomDatabase() {

    abstract fun pokemonDao(): PokemonDao
    abstract fun pokemonRemoteKeyDao(): PokemonRemoteKeyDao
    abstract fun pokemonDetailDao(): PokemonDetailDao
    abstract fun pokemonSpeciesDao(): PokemonSpeciesDao
    abstract fun pokemonEvolutionChainDao(): EvolutionChainDao

    companion object {
        @Volatile
        private var INSTANCE: RoomConfig? = null

        fun getDataBase(context: Context): RoomConfig {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RoomConfig::class.java,
                    "pokemon_database"
                ).fallbackToDestructiveMigration()
                    .build()

                INSTANCE = instance
                instance
            }
        }
    }
}