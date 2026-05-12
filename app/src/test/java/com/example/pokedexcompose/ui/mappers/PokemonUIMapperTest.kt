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
}
