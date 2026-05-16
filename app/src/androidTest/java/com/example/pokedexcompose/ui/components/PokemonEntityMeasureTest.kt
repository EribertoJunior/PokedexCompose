package com.example.pokedexcompose.ui.components

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import com.example.pokedexcompose.R
import com.example.pokedexcompose.ui.theme.PokedexComposeTheme
import org.junit.Rule
import org.junit.Test

class PokemonEntityMeasureTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun pokemonMeasure_shouldDisplayFormattedValueAndLabel() {
        composeTestRule.setContent {
            PokedexComposeTheme {
                PokemonMeasure(
                    formattedMeasure = "25.30 m",
                    iconId = R.drawable.ruler_square,
                    iconDescription = R.string.height,
                    iconContentDescription = R.string.pokemon_height_image_description
                )
            }
        }

        composeTestRule.onNodeWithText("25.30 m").assertIsDisplayed()
        composeTestRule.onNodeWithText("Altura").assertIsDisplayed()
    }

    @Test
    fun pokemonMeasure_shouldHaveCorrectContentDescription() {
        composeTestRule.setContent {
            PokedexComposeTheme {
                PokemonMeasure(
                    formattedMeasure = "25.30 m",
                    iconId = R.drawable.ruler_square,
                    iconDescription = R.string.height,
                    iconContentDescription = R.string.pokemon_height_image_description
                )
            }
        }

        composeTestRule
            .onNodeWithContentDescription("Altura em metros")
            .assertIsDisplayed()
    }
}
