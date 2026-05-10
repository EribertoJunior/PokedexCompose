package com.example.pokedexcompose.ui.components

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.printToLog
import com.example.pokedexcompose.data.local.enums.TypeColoursEnum
import com.example.pokedexcompose.ui.theme.PokedexComposeTheme
import org.junit.Rule
import org.junit.Test
import java.util.Locale

class PokemonEntityTypeTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun pokemonTypeTest_anItemToShow() {
        val typeColoursEnum = TypeColoursEnum.FIRE
        composeTestRule.setContent {
            PokedexComposeTheme {
                PokemonType(typeColoursEnum = typeColoursEnum)
            }
        }

        composeTestRule.onRoot().printToLog("currentLabelExists")

        composeTestRule
            .onNodeWithText(typeColoursEnum.name.lowercase().replaceFirstChar {
                it.titlecase(Locale.getDefault())
            }
            )
            .assertExists()
    }

    @Test
    fun pokemonTypeTest_contentDescriptionExist() {
        val typeColoursEnum = TypeColoursEnum.FIRE
        composeTestRule.setContent {
            PokedexComposeTheme {
                PokemonType(typeColoursEnum = typeColoursEnum)
            }
        }

        composeTestRule.onRoot().printToLog("currentLabelExists")

        composeTestRule
            .onNodeWithContentDescription("Tipo FIRE")
            .assertExists()
    }

}