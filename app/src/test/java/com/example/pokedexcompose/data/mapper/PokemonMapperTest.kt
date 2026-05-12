package com.example.pokedexcompose.data.mapper

import com.example.pokedexcompose.data.local.entities.PokemonDetail
import com.example.pokedexcompose.data.local.entities.PokemonEntity
import com.example.pokedexcompose.data.local.entities.PokemonDetailStats
import com.example.pokedexcompose.data.local.entities.Stat
import com.example.pokedexcompose.data.local.enums.TypeColoursEnum
import com.example.pokedexcompose.data.local.relations.PokemonAndDetail
import org.junit.Assert.assertEquals
import org.junit.Test

class PokemonMapperTest {

    @Test
    fun `toDomain should correctly map PokemonAndDetail to PokemonModel`() {
        // Given
        val pokemonAndDetail = PokemonAndDetail(
            pokemonEntity = PokemonEntity(pokemonId = 1, name = "bulbasaur", imageUrl = "url"),
            pokemonDetail = PokemonDetail(
                pokemonDetailId = 1,
                pokemonOwnerId = 1,
                colorTypeList = listOf(TypeColoursEnum.GRASS),
                weight = 69,
                height = 7,
                stats = listOf(
                    PokemonDetailStats(baseStat = 45, effort = 0, stat = Stat(name = "hp", url = ""))
                )
            )
        )

        // When
        val model = pokemonAndDetail.toDomain()

        // Then
        assertEquals(1, model.id)
        assertEquals("bulbasaur", model.name)
        assertEquals("url", model.imageUrl)
        assertEquals(listOf("GRASS"), model.types.map { it.name })
        assertEquals(69, model.weight)
        assertEquals(7, model.height)
        assertEquals(1, model.stats.size)
        assertEquals("hp", model.stats[0].name)
        assertEquals(45, model.stats[0].baseStat)
    }
}
