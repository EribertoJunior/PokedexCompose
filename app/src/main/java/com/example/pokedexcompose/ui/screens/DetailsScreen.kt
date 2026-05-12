package com.example.pokedexcompose.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.Absolute.SpaceBetween
import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pokedexcompose.ui.viewmodels.DetailsViewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.pokedexcompose.R
import com.example.pokedexcompose.extensions.color
import com.example.pokedexcompose.extensions.titlecase
import com.example.pokedexcompose.ui.components.ProgressBarStat
import com.example.pokedexcompose.ui.models.PokemonTypeUI
import com.example.pokedexcompose.ui.models.PokemonUI
import com.example.pokedexcompose.ui.theme.PokedexComposeTheme

@Composable
fun DetailsScreen(namePokemon: String, viewModel: DetailsViewModel) {
    val pokemonName = rememberSaveable { namePokemon }
    viewModel.searchEvolutionChain(pokemonName = pokemonName)
    val pokemonState by viewModel.uiState.collectAsState()

    DetailsScreen(pokemonState)
}

@Composable
fun DetailsScreen(pokemon: PokemonUI) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Box(
            Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
                .background(
                    brush = Brush.verticalGradient(
                        if (pokemon.types.isEmpty()) {
                            listOf(Color.LightGray, Color.Transparent)
                        } else {
                            pokemon.types.map { it.color.color } + Color.Transparent
                        }
                    )
                )
        ) {

            Column(
                Modifier
                    .align(Alignment.Center)
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = pokemon.name.titlecase,
                        fontWeight = FontWeight.Bold,
                        fontSize = 23.sp,
                        modifier = Modifier
                            .padding(top = 8.dp)
                    )

                    Text(
                        text = pokemon.idFormatted,
                        fontWeight = FontWeight.Bold,
                        fontSize = 23.sp,
                        modifier = Modifier
                            .padding(top = 8.dp)
                    )
                }

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
                    contentDescription = null,
                    modifier = Modifier
                        .size(250.dp)
                        .align(Alignment.CenterHorizontally),
                    contentScale = ContentScale.Crop,
                )
            }
        }

        if (pokemon.description.isNotEmpty()) {
            Text(
                text = pokemon.description,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                textAlign = TextAlign.Center
            )
        }

        Card(
            modifier = Modifier
                .padding(all = 8.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(15.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Column(verticalArrangement = spacedBy(8.dp)) {
                Text(
                    text = "Base stats",
                    modifier = Modifier.padding(start = 8.dp, top = 8.dp),
                    fontWeight = FontWeight.Bold
                )

                pokemon.stats.forEach { stat ->
                    Row(
                        modifier = Modifier
                            .padding(horizontal = 8.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = SpaceBetween
                    ) {

                        Text(
                            text = stat.name,
                        )

                        val widthBar = rememberSaveable { 200 }

                        val progressBarStatContentDescription = stringResource(
                            R.string.content_description_progress_bar_stat,
                            stat.name
                        )
                        ProgressBarStat(
                            modifier = Modifier
                                .clearAndSetSemantics {
                                    contentDescription = progressBarStatContentDescription
                                }
                                .clip(RoundedCornerShape(15.dp))
                                .height(20.dp)
                                .width(widthBar.dp)
                                .background(Color.Gray),
                            widthOfInnerBar = widthBar.dp,
                            colors = pokemon.types.map { it.color.color },
                            statValue = stat.value,
                            progress = stat.progress
                        )
                    }
                }

                Spacer(modifier = Modifier)
            }
        }

        if (pokemon.evolutionChain.isNotEmpty()) {
            Row(modifier = Modifier
                .padding(all = 8.dp)
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                pokemon.evolutionChain.forEach {
                    Image(
                        painter = rememberAsyncImagePainter(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(it.imageUrl)
                                .crossfade(true)
                                .build(),
                            error = painterResource(
                                id = R.drawable.pokebola
                            ),
                            placeholder = painterResource(id = R.drawable.pokebola)
                        ),
                        contentDescription = null,
                        modifier = Modifier
                            .size(100.dp)
                            .align(CenterVertically),
                        contentScale = ContentScale.Crop,
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DetailsScreenPreview() {
    PokedexComposeTheme {
        Surface {
            DetailsScreen(
                PokemonUI(
                    name = "Charizard",
                    idFormatted = "#006",
                    description = "A CHARIZARD flies about in search of strong opponents...",
                    types = listOf(PokemonTypeUI("FIRE", "#EE8130")),
                    stats = listOf(
                        com.example.pokedexcompose.ui.models.PokemonStatUI("HP", 78, 0.3f),
                        com.example.pokedexcompose.ui.models.PokemonStatUI("ATK", 84, 0.33f)
                    )
                )
            )
        }
    }
}
