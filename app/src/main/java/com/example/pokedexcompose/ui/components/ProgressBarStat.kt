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
import com.example.pokedexcompose.ui.theme.PokedexComposeTheme

@Composable
fun ProgressBarStat(
    modifier: Modifier = Modifier,
    widthOfInnerBar: Dp,
    colors: List<Color>,
    statValue: Int,
    progress: Float
) {
    Box(
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(15.dp))
                .background(
                    Brush.horizontalGradient(
                        colors = when {
                            colors.isEmpty() -> listOf(Color.Gray, Color.Gray)
                            colors.size == 1 -> listOf(colors[0], colors[0])
                            else -> colors
                        }
                    )
                )
                .width(widthOfInnerBar * progress)
        ) {
            Text(
                text = "$statValue",
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
                    Color.Red,
                    Color.Blue
                ),
                statValue = 90,
                progress = 0.5f,
                widthOfInnerBar = 200.dp
            )
        }
    }
}
