package com.example.pokedexcompose.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.printToLog
import androidx.compose.ui.unit.dp
import com.example.pokedexcompose.R
import com.example.pokedexcompose.data.local.entities.PokemonDetailStats
import com.example.pokedexcompose.data.local.entities.Stat
import com.example.pokedexcompose.data.local.enums.TypeColoursEnum
import com.example.pokedexcompose.extensions.color
import com.example.pokedexcompose.ui.theme.PokedexComposeTheme
import org.junit.Rule
import org.junit.Test

class ProgressBarStatTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun progressBarStatTest() {
        composeTestRule.setContent {
            PokedexComposeTheme {

                val progressBarStatContentDescription = stringResource(
                    R.string.content_description_progress_bar_stat,
                    "hp"
                )
                ProgressBarStat(
                    modifier = Modifier
                        .clearAndSetSemantics {
                            contentDescription = progressBarStatContentDescription
                        }
                        .clip(RoundedCornerShape(15.dp))
                        .height(20.dp)
                        .width(200.dp)
                        .background(Color.Gray),
                    colors = listOf(
                        TypeColoursEnum.FIRE.codColor.color,
                        TypeColoursEnum.DRAGON.codColor.color
                    ),
                    pokemonDetailStats = PokemonDetailStats(
                        baseStat = 90,
                        effort = 2,
                        stat = Stat(
                            name = "HP",
                            url = ""
                        )
                    ),
                    widthOfInnerBar = 200.dp
                )
            }
        }

        composeTestRule.onRoot().printToLog("progressBarStatTest")

        composeTestRule
            .onNodeWithContentDescription("Barra de progresso de hp")
            .assertExists()
    }
}