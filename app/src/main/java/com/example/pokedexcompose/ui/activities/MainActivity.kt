package com.example.pokedexcompose.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import com.example.pokedexcompose.ui.activities.DetailsActivity.Companion.DETAILS_ACTIVITY_POKEMON_NAME
import com.example.pokedexcompose.ui.screens.HomeScreen
import com.example.pokedexcompose.ui.theme.PokedexComposeTheme
import org.koin.androidx.compose.getViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokedexComposeTheme {
                App(
                    content = {
                        HomeScreen(viewModel = getViewModel(), onClickPokemon = {
                            startActivity(
                                Intent(this, DetailsActivity::class.java)
                                    .run { putExtra(DETAILS_ACTIVITY_POKEMON_NAME, it.name) })
                        })
                    }
                )
            }
        }
    }
}

@Composable
fun App(content: @Composable () -> Unit = {}) {
    PokedexComposeTheme {
        Surface {
            content()
        }
    }
}