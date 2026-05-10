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
import com.example.pokedexcompose.data.local.entities.Home
import com.example.pokedexcompose.data.local.entities.OfficialArtwork
import com.example.pokedexcompose.data.local.entities.Other
import com.example.pokedexcompose.data.local.entities.PokemonEntity
import com.example.pokedexcompose.data.local.entities.PokemonDetail
import com.example.pokedexcompose.data.local.entities.PokemonDetailSpecies
import com.example.pokedexcompose.data.local.entities.Sprites
import com.example.pokedexcompose.data.local.relations.PokemonAndDetail
import com.example.pokedexcompose.data.local.enums.TypeColoursEnum
import com.example.pokedexcompose.extensions.color
import com.example.pokedexcompose.extensions.titlecase
import com.example.pokedexcompose.extensions.toDoubleFormat
import com.example.pokedexcompose.ui.theme.PokedexComposeTheme

@Composable
fun PokemonItem(
    pokemonAndDetail: PokemonAndDetail,
    onClickPokemon: (PokemonAndDetail) -> Unit = {}
) {
    Card(
        modifier = Modifier.clickable { onClickPokemon(pokemonAndDetail) },
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
                            pokemonAndDetail.pokemonDetail.colorTypeList
                                .map { it.codColor.color }
                                .plus(Color.Transparent)
                        )
                    )
            ) {
                Column(Modifier.align(Center)) {
                    Image(
                        painter = rememberAsyncImagePainter(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(pokemonAndDetail.pokemonEntity.imageUrl)
                                .crossfade(true)
                                .build(),
                            error = painterResource(
                                id = R.drawable.pokebola
                            ),
                            placeholder = painterResource(id = R.drawable.pokebola)
                        ),
                        contentDescription = stringResource(
                            R.string.pokemon_image_content_description,
                            pokemonAndDetail.pokemonEntity.name
                        ),
                        modifier = Modifier
                            .size(130.dp)
                            .align(CenterHorizontally),
                        contentScale = ContentScale.Crop,
                    )
                    Text(
                        text = pokemonAndDetail.pokemonEntity.idFormatted,
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
                    text = pokemonAndDetail.pokemonEntity.name.titlecase,
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
                    pokemonAndDetail.pokemonDetail.colorTypeList.forEach {
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
                        formattedMeasure = "${
                            pokemonAndDetail.pokemonDetail.weight.toDoubleFormat(
                                2
                            )
                        } kg",
                        iconId = R.drawable.weight_kilogram,
                        iconDescription = R.string.weight,
                        iconContentDescription = R.string.pokemon_weight_image_description
                    )
                    PokemonMeasure(
                        formattedMeasure = "${
                            pokemonAndDetail.pokemonDetail.height.toDoubleFormat(
                                2
                            )
                        } m",
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
                PokemonAndDetail(
                    pokemonEntity = PokemonEntity(
                        pokemonId = 10,
                        name = "Teste",
                        imageUrl = ""
                    ),
                    pokemonDetail = PokemonDetail(
                        colorTypeList = listOf(
                            TypeColoursEnum.DRAGON,
                            TypeColoursEnum.FIGHTING
                        ),
                        sprites = Sprites(
                            Other(
                                officialArtwork = OfficialArtwork(""),
                                home = Home("")
                            )
                        ),
                        weight = 238,
                        height = 13,
                        stats = emptyList(),
                        species = PokemonDetailSpecies(
                            name = "",
                            url = ""
                        )
                    ),
                )
            )
        }
    }
}
