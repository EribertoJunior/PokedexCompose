package com.example.pokedexcompose.ui.mappers

import com.example.pokedexcompose.domain.model.PokemonModel
import com.example.pokedexcompose.domain.model.PokemonStatModel
import com.example.pokedexcompose.domain.model.PokemonTypeModel
import org.junit.Assert.assertEquals
import org.junit.Test

class PokemonUIMapperTest {

    @Test
    fun `toUI should correctly map PokemonModel to PokemonUI`() {
        // Given
        val model = PokemonModel(
            id = 1,
            name = "bulbasaur",
            imageUrl = "url",
            types = listOf(PokemonTypeModel("GRASS", "#7AC74C")),
            weight = 69,
            height = 7,
            stats = listOf(PokemonStatModel("hp", 45))
        )

        // When
        val ui = model.toUI()

        // Then
        assertEquals(1, ui.id)
        assertEquals("bulbasaur", ui.name)
        assertEquals("#001", ui.idFormatted)
        assertEquals("6.90 kg", ui.weight)
        assertEquals("0.70 m", ui.height)
        assertEquals(1, ui.stats.size)
        assertEquals("HP", ui.stats[0].name)
        assertEquals(45, ui.stats[0].value)
        assertEquals(45 / 255f, ui.stats[0].progress, 0.001f)
    }

    @Test
    fun `toUI should turn the PokeAPI flavor text into a single-spaced paragraph`() {
        // Given: texto real de pokemon-species/1 (versão red), com \n e \u000c (form feed)
        val model = PokemonModel(
            id = 1,
            name = "bulbasaur",
            description = "A strange seed was\nplanted on its\nback at birth.\u000cThe plant sprouts\nand grows with\nthis POKéMON."
        )

        // When
        val ui = model.toUI()

        // Then
        assertEquals(
            "A strange seed was planted on its back at birth. The plant sprouts and grows with this POKéMON.",
            ui.description
        )
    }

    @Test
    fun `toUI should collapse whitespace runs and trim the description`() {
        // Given
        val model = PokemonModel(
            id = 6,
            name = "charizard",
            description = "  It breathes\r\nflames.\tHowever,  \nit will\u000c\u000cnever torch a weaker foe.\n"
        )

        // When
        val ui = model.toUI()

        // Then
        assertEquals("It breathes flames. However, it will never torch a weaker foe.", ui.description)
    }
}
