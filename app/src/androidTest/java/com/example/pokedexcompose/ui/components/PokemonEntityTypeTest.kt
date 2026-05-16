package com.example.pokedexcompose.ui.components

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import com.example.pokedexcompose.ui.models.PokemonTypeUI
import com.example.pokedexcompose.ui.theme.PokedexComposeTheme
import org.junit.Rule
import org.junit.Test

class PokemonEntityTypeTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun pokemonType_shouldDisplayTypeName() {
        val typeUI = PokemonTypeUI("FIRE", "#EE8130")
        composeTestRule.setContent {
            PokedexComposeTheme {
                PokemonType(type = typeUI)
            }
        }

        composeTestRule.onNodeWithText("Fire").assertIsDisplayed()
    }

    @Test
    fun pokemonType_shouldDisplayContentDescription() {
        val typeUI = PokemonTypeUI("FIRE", "#EE8130")
        composeTestRule.setContent {
            PokedexComposeTheme {
                PokemonType(type = typeUI)
            }
        }

        composeTestRule
            .onNodeWithContentDescription("Tipo FIRE")
            .assertIsDisplayed()
    }

}
