package com.example.pokedexcompose.data.mapper

import com.example.pokedexcompose.data.local.relations.PokemonAndDetail
import com.example.pokedexcompose.domain.model.PokemonEvolutionModel
import com.example.pokedexcompose.domain.model.PokemonModel
import com.example.pokedexcompose.domain.model.PokemonStatModel
import com.example.pokedexcompose.domain.model.PokemonTypeModel

fun PokemonAndDetail.toDomain(): PokemonModel {
    return PokemonModel(
        id = this.pokemonEntity.pokemonId,
        name = this.pokemonEntity.name,
        imageUrl = this.pokemonEntity.imageUrl,
        types = this.pokemonDetail.colorTypeList.map {
            PokemonTypeModel(
                name = it.name,
                color = it.codColor
            )
        },
        weight = this.pokemonDetail.weight,
        height = this.pokemonDetail.height,
        stats = this.pokemonDetail.stats.map {
            PokemonStatModel(
                name = it.stat.name,
                baseStat = it.baseStat
            )
        },
        description = this.specieAndEvolutionChain?.pokemonSpeciesEntity?.flavorTextEntries?.flavorText ?: "",
        evolutionChain = this.specieAndEvolutionChain?.evolutionChainEntity?.evolutionList?.map {
            PokemonEvolutionModel(
                name = it.name,
                imageUrl = it.imageUrl
            )
        } ?: emptyList()
    )
}
