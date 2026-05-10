package com.example.pokedexcompose.ui.components

import android.content.Context
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pokedexcompose.R
import com.example.pokedexcompose.data.local.enums.TypeColoursEnum
import com.example.pokedexcompose.extensions.color
import java.util.Locale

@Composable
fun PokemonType(typeColoursEnum: TypeColoursEnum) {
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = typeColoursEnum.codColor.color,
            contentColor = Color.White
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(4.dp)
        ) {
            Image(
                painter = painterResource(
                    id = getDrawableId(
                        typeName = typeColoursEnum.name,
                        LocalContext.current
                    )
                ),
                contentDescription = stringResource(R.string.content_description_type, typeColoursEnum.name),
                modifier = Modifier
                    .width(20.dp)
                    .height(20.dp)
            )

            Text(
                text = typeColoursEnum.name.lowercase().replaceFirstChar {
                    if (it.isLowerCase())
                        it.titlecase(Locale.getDefault())
                    else
                        it.toString()
                },
                modifier = Modifier.padding(horizontal = 4.dp)
            )
        }
    }
}

private fun getDrawableId(typeName: String, context: Context) =
    context.resources.getIdentifier("ic_${typeName.lowercase()}", "drawable", context.packageName)

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun PokemonTypePreview() {
    PokemonType(TypeColoursEnum.GROUND)
}
