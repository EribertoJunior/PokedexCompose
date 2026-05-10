package com.example.pokedexcompose.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pokedexcompose.data.local.entities.PokemonDetailStats
import com.example.pokedexcompose.data.local.entities.Stat
import com.example.pokedexcompose.data.local.enums.TypeColoursEnum
import com.example.pokedexcompose.extensions.color
import com.example.pokedexcompose.ui.theme.PokedexComposeTheme

@Composable
fun ProgressBarStat(
    modifier: Modifier = Modifier,
    widthOfInnerBar: Int,
    colorTypeList: List<TypeColoursEnum>,
    pokemonDetailStats: PokemonDetailStats
) {

    ProgressBarStat(
        modifier = modifier,
        widthOfInnerBar = widthOfInnerBar.dp,
        pokemonDetailStats = pokemonDetailStats,
        colors = if (colorTypeList.size == 1) {
            listOf(
                colorTypeList[0].codColor.color,
                colorTypeList[0].codColor.color
            )
        } else {
            colorTypeList.map { it.codColor.color }
        }
    )
}

@Composable
fun ProgressBarStat(
    modifier: Modifier = Modifier,
    widthOfInnerBar: Dp,
    colors: List<Color>,
    pokemonDetailStats: PokemonDetailStats
) {
    Box(
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(15.dp))
                .background(Brush.horizontalGradient(
                    colors = colors.map {
                        it
                    }
                ))
                .width(widthOfInnerBar * pokemonDetailStats.baseStat / 100)
        ) {
            Text(
                text = "${pokemonDetailStats.baseStat}",
                modifier = Modifier.align(Alignment.Center),
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ProgressBarStatPreview() {
    PokedexComposeTheme {
        Surface {
            ProgressBarStat(
                modifier = Modifier
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
}