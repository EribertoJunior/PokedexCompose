package com.example.pokedexcompose.ui.screens

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.pokedexcompose.ui.models.PokemonTypeUI
import com.example.pokedexcompose.ui.models.PokemonUI
import com.example.pokedexcompose.ui.theme.PokedexComposeTheme
import kotlinx.coroutines.flow.flowOf
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class HomeScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun homeScreen_shouldDisplayPokemonList() {
        val pokemons = listOf(
            PokemonUI(id = 1, name = "Bulbasaur", idFormatted = "#001", types = listOf(PokemonTypeUI("GRASS", "#7AC74C"))),
            PokemonUI(id = 2, name = "Ivysaur", idFormatted = "#002", types = listOf(PokemonTypeUI("GRASS", "#7AC74C")))
        )
        val pagingData = PagingData.from(pokemons)
        val flow = flowOf(pagingData)

        composeTestRule.setContent {
            PokedexComposeTheme {
                HomeScreenView(
                    pokemonLazyPagingItems = flow.collectAsLazyPagingItems()
                )
            }
        }

        composeTestRule.onNodeWithText("Bulbasaur").assertIsDisplayed()
        composeTestRule.onNodeWithText("Ivysaur").assertIsDisplayed()
        composeTestRule.onNodeWithText("#001").assertIsDisplayed()
        composeTestRule.onNodeWithText("#002").assertIsDisplayed()
    }

    @Test
    fun homeScreen_whenPokemonClicked_shouldTriggerCallback() {
        var clickedPokemon: PokemonUI? = null
        val pokemons = listOf(
            PokemonUI(id = 1, name = "Bulbasaur", idFormatted = "#001")
        )
        val pagingData = PagingData.from(pokemons)
        val flow = flowOf(pagingData)

        composeTestRule.setContent {
            PokedexComposeTheme {
                HomeScreenView(
                    pokemonLazyPagingItems = flow.collectAsLazyPagingItems(),
                    onClickPokemon = { clickedPokemon = it }
                )
            }
        }

        composeTestRule.onNodeWithText("Bulbasaur").performClick()

        assertEquals("Bulbasaur", clickedPokemon?.name)
    }
}
