package com.example.pokedexcompose.extensions

import androidx.compose.ui.graphics.Color
import org.junit.Assert.assertEquals
import org.junit.Test

class ExtensionsTest {

    @Test
    fun `String color should correctly parse hex to Color`() {
        // Given
        val hex = "#FF0000" // Red

        // When
        val color = hex.color

        // Then
        assertEquals(Color.Red, color)
    }

    @Test
    fun `String getUrlId should extract id from pokeapi url`() {
        // Given
        val url = "https://pokeapi.co/api/v2/pokemon-species/25/"

        // When
        val id = url.getUrlId

        // Then
        assertEquals(25, id)
    }

    @Test
    fun `String titlecase should capitalize first letter`() {
        // Given
        val name = "pikachu"

        // When
        val result = name.titlecase

        // Then
        assertEquals("Pikachu", result)
    }

    @Test
    fun `Int toDoubleFormat should format correctly with decimals`() {
        // Given
        val value = 69 // 6.9

        // When
        val result = value.toDoubleFormat(2)

        // Then
        assertEquals("6.90", result)
    }
}
