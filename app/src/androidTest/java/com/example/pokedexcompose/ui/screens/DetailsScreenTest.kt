package com.example.pokedexcompose.ui.screens

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performScrollTo
import com.example.pokedexcompose.ui.models.PokemonStatUI
import com.example.pokedexcompose.ui.models.PokemonTypeUI
import com.example.pokedexcompose.ui.models.PokemonUI
import com.example.pokedexcompose.ui.theme.PokedexComposeTheme
import org.junit.Rule
import org.junit.Test

class DetailsScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun detailsScreen_shouldDisplayPokemonDetails() {
        val pokemon = PokemonUI(
            id = 6,
            name = "Charizard",
            idFormatted = "#006",
            description = "Spits fire that is hot enough to melt boulders.",
            types = listOf(PokemonTypeUI("FIRE", "#EE8130")),
            weight = "90.50 kg",
            height = "1.70 m",
            stats = listOf(
                PokemonStatUI("HP", 78, 0.3f),
                PokemonStatUI("ATK", 84, 0.33f)
            )
        )

        composeTestRule.setContent {
            PokedexComposeTheme {
                DetailsScreen(pokemon = pokemon)
            }
        }

        composeTestRule.onNodeWithText("Charizard").assertIsDisplayed()
        composeTestRule.onNodeWithText("#006").assertIsDisplayed()
        composeTestRule.onNodeWithText("Spits fire that is hot enough to melt boulders.").performScrollTo().assertIsDisplayed()
        composeTestRule.onNodeWithText("90.50 kg").performScrollTo().assertIsDisplayed()
        composeTestRule.onNodeWithText("1.70 m").performScrollTo().assertIsDisplayed()
        composeTestRule.onNodeWithText("HP").performScrollTo().assertIsDisplayed()
        composeTestRule.onNodeWithText("ATK").performScrollTo().assertIsDisplayed()
        composeTestRule.onNodeWithText("78").performScrollTo().assertIsDisplayed()
        composeTestRule.onNodeWithText("84").performScrollTo().assertIsDisplayed()
    }
}
