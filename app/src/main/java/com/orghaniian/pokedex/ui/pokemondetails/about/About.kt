package com.orghaniian.pokedex.ui.pokemondetails.about

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.orghaniian.data.model.Type
import com.orghaniian.pokedex.R
import com.orghaniian.pokedex.ui.pokemondetails.PokemonDetailsUiState
import java.text.DecimalFormat

@Composable
fun About(
    pokemon: PokemonDetailsUiState.Pokemon
) {
    Row {
        Column {
            Text(
                text = stringResource(R.string.heightLabel)
            )
            Text(
                text = stringResource(R.string.weightLabel)
            )
            Text(
                text = stringResource(R.string.genderLabel)
            )
        }
        Column(
            modifier = Modifier.padding(start = 48.dp)
        ) {
            Text(
                text = "${pokemon.height * 100} cm"
            )
            Text(
                text = "${pokemon.weight} kg"
            )
            val df = DecimalFormat("##.#%")
            Text(
                text = if (pokemon.genderRate > 0)
                        "${df.format(pokemon.genderRate)} ${df.format(1 - pokemon.genderRate)}"
                    else "none"
            )
        }
    }
}

@Preview
@Composable
private fun PreviewAbout(){
    MaterialTheme {
        About(
            PokemonDetailsUiState.Pokemon(
                "Bublizarre",
                1,
                listOf(Type.GRASS, Type.POISON),
                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png",
                com.orghaniian.data.model.Color.GREEN,
                0.1f,
                2f,
                .7f
            )
        )
    }
}