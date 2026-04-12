package com.example.pokedexcompose.data.dataBase.local.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.pokedexcompose.data.dataBase.local.converters.ConverterPokemonDetailStats
import com.example.pokedexcompose.data.dataBase.local.converters.ConverterTypeColoursEnum
import com.example.pokedexcompose.data.model.local.enums.TypeColoursEnum

@Entity
data class PokemonDetail(
    @PrimaryKey
    val pokemonDetailId: Int = 0,
    val pokemonOwnerId: Int = 0,
    val weight: Int = 0,
    val height: Int = 0,
    @Embedded(prefix = "sprites_")
    val sprites: Sprites? = null,
    @TypeConverters(ConverterTypeColoursEnum::class)
    val colorTypeList: List<TypeColoursEnum> = listOf(TypeColoursEnum.DARK),
    @Embedded(prefix = "species_")
    val species: PokemonDetailSpecies? = null,
    @TypeConverters(ConverterPokemonDetailStats::class)
    val stats: List<PokemonDetailStats> =  emptyList()
) {
    companion object {
        const val POKEMON_DETAIL_ID = "pokemonDetailId"
        const val POKEMON_DETAIL_OWNER_ID = "pokemonOwnerId"
    }
}

data class PokemonDetailStats(
    val baseStat: Int,
    val effort: Int,
    val stat: Stat,
)

data class Stat(
    val name: String,
    val url: String
)

data class Sprites(
    @Embedded(prefix = "other_") val other: Other?
)

data class PokemonDetailSpecies(
    val name: String?,
    val url: String?
)

data class Other(
    @Embedded(prefix = "official_artwork_") val officialArtwork: OfficialArtwork?,
    @Embedded(prefix = "home_") val home: Home?,
)

data class Home(
    val frontDefault: String?
)

data class OfficialArtwork(
    val frontDefault: String?
)