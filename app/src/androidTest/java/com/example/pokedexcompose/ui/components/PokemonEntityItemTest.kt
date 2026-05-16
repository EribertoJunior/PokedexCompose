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

class PokemonEntityItemTest {

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
    fun pokemonItemTest_pokemonImage() {
        composeTestRule.setContent {
            PokedexComposeTheme {
                PokemonItem(pokemon = pokemonUI)
            }
        }

        composeTestRule
            .onNodeWithContentDescription("Imagem do pokémon Teste")
            .assertIsDisplayed()
    }

    @Test
    fun pokemonItemTest_pokemonId() {
        composeTestRule.setContent {
            PokedexComposeTheme {
                PokemonItem(pokemon = pokemonUI)
            }
        }

        composeTestRule
            .onNodeWithText("#010")
            .assertIsDisplayed()
    }

    @Test
    fun pokemonItemTest_pokemonName() {
        composeTestRule.setContent {
            PokedexComposeTheme {
                PokemonItem(pokemon = pokemonUI)
            }
        }

        composeTestRule
            .onNodeWithText("Teste")
            .assertIsDisplayed()
    }

    @Test
    fun pokemonItemTest_pokemonImageType() {
        composeTestRule.setContent {
            PokedexComposeTheme {
                PokemonItem(pokemon = pokemonUI)
            }
        }

        composeTestRule
            .onNodeWithContentDescription("Tipo DRAGON")
            .assertIsDisplayed()
    }

    @Test
    fun pokemonItemTest_secondPokemonImageType() {
        composeTestRule.setContent {
            PokedexComposeTheme {
                PokemonItem(pokemon = pokemonUI)
            }
        }

        composeTestRule
            .onNodeWithContentDescription("Tipo FIGHTING")
            .assertIsDisplayed()
    }

    @Test
    fun pokemonItemTest_pokemonTypeName() {
        composeTestRule.setContent {
            PokedexComposeTheme {
                PokemonItem(pokemon = pokemonUI)
            }
        }

        composeTestRule
            .onNodeWithText("Dragon")
            .assertIsDisplayed()
    }

    @Test
    fun pokemonItemTest_secondPokemonTypeName() {
        composeTestRule.setContent {
            PokedexComposeTheme {
                PokemonItem(pokemon = pokemonUI)
            }
        }

        composeTestRule
            .onNodeWithText("Fighting")
            .assertIsDisplayed()
    }

    @Test
    fun pokemonItemTest_imageWeightInKilogram() {
        composeTestRule.setContent {
            PokedexComposeTheme {
                PokemonItem(pokemon = pokemonUI)
            }
        }

        composeTestRule
            .onNodeWithContentDescription("Peso em quilograma")
            .assertIsDisplayed()
    }

    @Test
    fun pokemonItemTest_valueTextKilogram() {
        composeTestRule.setContent {
            PokedexComposeTheme {
                PokemonItem(pokemon = pokemonUI)
            }
        }

        composeTestRule
            .onNodeWithText("23.8 kg")
            .assertIsDisplayed()
    }

    @Test
    fun pokemonItemTest_imageHeightInMeters() {
        composeTestRule.setContent {
            PokedexComposeTheme {
                PokemonItem(pokemon = pokemonUI)
            }
        }

        composeTestRule
            .onNodeWithContentDescription("Altura em metros")
            .assertIsDisplayed()
    }

    @Test
    fun pokemonItemTest_valueTextHeight() {
        composeTestRule.setContent {
            PokedexComposeTheme {
                PokemonItem(pokemon = pokemonUI)
            }
        }

        composeTestRule
            .onNodeWithText("1.3 m")
            .assertIsDisplayed()
    }

    @Test
    fun pokemonItemTest_textLabels() {
        composeTestRule.setContent {
            PokedexComposeTheme {
                PokemonItem(pokemon = pokemonUI)
            }
        }

        composeTestRule.onNodeWithText("Peso").assertIsDisplayed()
        composeTestRule.onNodeWithText("Altura").assertIsDisplayed()
    }
}
