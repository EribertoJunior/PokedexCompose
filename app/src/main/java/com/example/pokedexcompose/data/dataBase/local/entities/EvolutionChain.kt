package com.example.pokedexcompose.data.dataBase.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.pokedexcompose.data.dataBase.local.converters.ConverterSpecieToEvolution

@Entity
data class EvolutionChain(
    @PrimaryKey val evolutionChainId: Int,
    val evolutionList: List<SpecieToEvolution>
){
    companion object{
        const val EVOLUTION_CHAIN_ID = "evolutionChainId"
    }
}

data class SpecieToEvolution(
    val name: String,
    val imageUrl: String,
)
