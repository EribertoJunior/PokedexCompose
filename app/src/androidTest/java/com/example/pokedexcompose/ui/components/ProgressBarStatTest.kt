package com.example.pokedexcompose.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.unit.dp
import com.example.pokedexcompose.R
import com.example.pokedexcompose.ui.theme.PokedexComposeTheme
import org.junit.Rule
import org.junit.Test

class ProgressBarStatTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun progressBarStat_shouldDisplayValueAndContentDescription() {
        composeTestRule.setContent {
            PokedexComposeTheme {
                val progressBarStatContentDescription = stringResource(
                    R.string.content_description_progress_bar_stat,
                    "hp"
                )
                ProgressBarStat(
                    modifier = Modifier
                        .height(20.dp)
                        .width(200.dp)
                        .semantics {
                            contentDescription = progressBarStatContentDescription
                        }
                        .clip(RoundedCornerShape(15.dp))
                        .background(Color.Gray),
                    colors = listOf(Color.Red, Color.Yellow),
                    statValue = 90,
                    progress = 0.5f,
                    widthOfInnerBar = 200.dp
                )
            }
        }

        composeTestRule.onNodeWithText("90").assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("Barra de progresso de hp").assertIsDisplayed()
    }
}
