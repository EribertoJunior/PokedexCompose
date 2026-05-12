package com.example.pokedexcompose.ui.mappers

import com.example.pokedexcompose.domain.model.PokemonModel
import com.example.pokedexcompose.extensions.toDoubleFormat
import com.example.pokedexcompose.ui.models.PokemonEvolutionUI
import com.example.pokedexcompose.ui.models.PokemonStatUI
import com.example.pokedexcompose.ui.models.PokemonTypeUI
import com.example.pokedexcompose.ui.models.PokemonUI

fun PokemonModel.toUI(): PokemonUI {
    return PokemonUI(
        id = this.id,
        name = this.name,
        imageUrl = this.imageUrl,
        types = this.types.map {
            PokemonTypeUI(
                name = it.name,
                color = it.color
            )
        },
        weight = "${this.weight.toDoubleFormat(2)} kg",
        height = "${this.height.toDoubleFormat(2)} m",
        idFormatted = formatId(this.id),
        description = this.description.replace("\n", " "),
        stats = this.stats.map {
            PokemonStatUI(
                name = parseStatName(it.name),
                value = it.baseStat,
                progress = it.baseStat / 255f
            )
        },
        evolutionChain = this.evolutionChain.map {
            PokemonEvolutionUI(
                name = it.name,
                imageUrl = it.imageUrl
            )
        }
    )
}

private fun formatId(id: Int): String {
    return if (id < 10) {
        "#00$id"
    } else if (id in 10..99) {
        "#0$id"
    } else {
        "#$id"
    }
}

private fun parseStatName(name: String): String {
    return when (name.lowercase()) {
        "hp" -> "HP"
        "attack" -> "ATK"
        "defense" -> "DEF"
        "special-attack" -> "SATK"
        "special-defense" -> "SDEF"
        "speed" -> "SPD"
        else -> name.uppercase()
    }
}
