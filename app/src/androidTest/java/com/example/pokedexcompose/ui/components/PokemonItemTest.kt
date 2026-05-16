package com.example.pokedexcompose.ui.components

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import com.example.pokedexcompose.ui.models.PokemonTypeUI
import com.example.pokedexcompose.ui.models.PokemonUI
import com.example.pokedexcompose.ui.theme.PokedexComposeTheme
import org.junit.Rule
import org.junit.Test

class PokemonItemTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val pokemonUI = PokemonUI(
        id = 10,
        name = "Teste",
        idFormatted = "#010",
        imageUrl = "",
        types = listOf(
            PokemonTypeUI("DRAGON", "#6F35FC"),
            PokemonTypeUI("FIGHTING", "#C22E28")
        ),
        weight = "23.8 kg",
        height = "1.3 m"
    )

    @Test
    fun pokemonItem_shouldDisplayBasicInfo() {
        composeTestRule.setContent {
            PokedexComposeTheme {
                PokemonItem(pokemon = pokemonUI)
            }
        }

        composeTestRule.onNodeWithText("Teste").assertIsDisplayed()
        composeTestRule.onNodeWithText("#010").assertIsDisplayed()
        composeTestRule.onNodeWithText("23.8 kg").assertIsDisplayed()
        composeTestRule.onNodeWithText("1.3 m").assertIsDisplayed()
    }

    @Test
    fun pokemonItem_shouldDisplayTypes() {
        composeTestRule.setContent {
            PokedexComposeTheme {
                PokemonItem(pokemon = pokemonUI)
            }
        }

        composeTestRule.onNodeWithContentDescription("Tipo DRAGON").assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("Tipo FIGHTING").assertIsDisplayed()
    }
}
