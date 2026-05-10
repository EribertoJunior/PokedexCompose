package com.example.pokedexcompose.ui.components

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.printToLog
import com.example.pokedexcompose.R
import com.example.pokedexcompose.extensions.toDoubleFormat
import org.junit.Rule
import org.junit.Test


class PokemonEntityMeasureTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun pokemonMeasureTest() {
        composeTestRule.setContent {
            PokemonMeasure(
                formattedMeasure = 253.toDoubleFormat(2),
                iconId = R.drawable.ruler_square,
                iconDescription = R.string.height,
                iconContentDescription = R.string.pokemon_height_image_description
            )
        }

        composeTestRule.onRoot(useUnmergedTree = true).printToLog("measureTest")

        composeTestRule
            .onNodeWithText("Altura")
            .assertExists()
    }

    @Test
    fun pokemonMeasureTest_contentDescriptionExist() {
        composeTestRule.setContent {
            PokemonMeasure(
                formattedMeasure = 253.toDoubleFormat(2),
                iconId = R.drawable.ruler_square,
                iconDescription = R.string.height,
                iconContentDescription = R.string.pokemon_height_image_description
            )
        }

        composeTestRule.onRoot(useUnmergedTree = true).printToLog("measureTest")

        composeTestRule
            .onNodeWithContentDescription("Altura em metros")
            .assertExists()
    }
}