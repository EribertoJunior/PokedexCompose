package com.example.pokedexcompose.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class EvolutionChainEntity(
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
