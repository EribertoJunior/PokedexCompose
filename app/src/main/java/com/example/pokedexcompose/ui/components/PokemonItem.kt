package com.example.pokedexcompose.ui.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.pokedexcompose.R
import com.example.pokedexcompose.extensions.color
import com.example.pokedexcompose.extensions.titlecase
import com.example.pokedexcompose.ui.models.PokemonTypeUI
import com.example.pokedexcompose.ui.models.PokemonUI
import com.example.pokedexcompose.ui.theme.PokedexComposeTheme

@Composable
fun PokemonItem(
    pokemon: PokemonUI,
    onClickPokemon: (PokemonUI) -> Unit = {}
) {
    Card(
        modifier = Modifier.clickable { onClickPokemon(pokemon) },
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .heightIn(min = 150.dp, max = 180.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(0.5f)
                    .background(
                        brush = Brush.horizontalGradient(
                            if (pokemon.types.isEmpty()) {
                                listOf(Color.LightGray, Color.Transparent)
                            } else {
                                pokemon.types.map { it.color.color } + Color.Transparent
                            }
                        )
                    )
            ) {
                Column(Modifier.align(Center)) {
                    Image(
                        painter = rememberAsyncImagePainter(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(pokemon.imageUrl)
                                .crossfade(true)
                                .build(),
                            error = painterResource(
                                id = R.drawable.pokebola
                            ),
                            placeholder = painterResource(id = R.drawable.pokebola)
                        ),
                        contentDescription = stringResource(
                            R.string.pokemon_image_content_description,
                            pokemon.name
                        ),
                        modifier = Modifier
                            .size(130.dp)
                            .align(CenterHorizontally),
                        contentScale = ContentScale.Crop,
                    )
                    Text(
                        text = pokemon.idFormatted,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .align(CenterHorizontally)
                    )
                }
            }

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = pokemon.name.titlecase,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .align(CenterHorizontally)
                        .padding(top = 8.dp)
                )

                Row(
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .padding(end = 4.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(
                        space = 4.dp,
                        alignment = CenterHorizontally
                    )
                ) {
                    pokemon.types.forEach {
                        PokemonType(it)
                    }
                }

                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .align(CenterHorizontally)
                ) {
                    PokemonMeasure(
                        formattedMeasure = pokemon.weight,
                        iconId = R.drawable.weight_kilogram,
                        iconDescription = R.string.weight,
                        iconContentDescription = R.string.pokemon_weight_image_description
                    )
                    PokemonMeasure(
                        formattedMeasure = pokemon.height,
                        iconId = R.drawable.ruler_square,
                        iconDescription = R.string.height,
                        iconContentDescription = R.string.pokemon_height_image_description
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun PokemonItemPreview() {
    PokedexComposeTheme {
        Surface {
            PokemonItem(
                PokemonUI(
                    idFormatted = "#010",
                    name = "Teste",
                    imageUrl = "",
                    types = listOf(
                        PokemonTypeUI("DRAGON", "#6F35FC"),
                        PokemonTypeUI("FIGHTING", "#C22E28")
                    ),
                    weight = "23.8 kg",
                    height = "1.3 m"
                )
            )
        }
    }
}
