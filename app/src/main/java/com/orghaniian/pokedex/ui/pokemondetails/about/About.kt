package com.orghaniian.pokedex.ui.pokemondetails.about

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.orghaniian.data.local.PokemonStat
import com.orghaniian.data.model.Stat
import com.orghaniian.data.model.Type
import com.orghaniian.pokedex.R
import com.orghaniian.pokedex.ui.pokemondetails.PokemonDetailsUiState
import com.orghaniian.pokedex.ui.theme.Dimensions
import com.orghaniian.pokedex.ui.theme.PokedexTheme
import java.text.DecimalFormat

@Composable
fun About(
    pokemon: PokemonDetailsUiState.Pokemon
) {
    val modifier = Modifier.height(
        with(LocalDensity.current) {
            MaterialTheme.typography.bodyLarge.lineHeight.toDp()
        }
    )
    Row {
        Column {
            Text(
                text = stringResource(R.string.heightLabel),
                modifier = modifier,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = stringResource(R.string.weightLabel),
                modifier = modifier,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = stringResource(R.string.genderLabel),
                modifier = modifier,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        Column(
            modifier = Modifier.padding(start = Dimensions.infoSpacing)
        ) {
            Text(
                text = "${pokemon.height * 100} cm",
                modifier = modifier
            )
            Text(
                text = "${pokemon.weight} kg",
                modifier = modifier
            )
            val df = DecimalFormat("##.#%")
            Text(
                text = if (pokemon.genderRate > 0)
                        "${df.format(pokemon.genderRate)} ${df.format(1 - pokemon.genderRate)}"
                    else "none",
                modifier = modifier
            )
        }
    }
}

@Preview
@Composable
private fun PreviewAbout(){
    PokedexTheme {
        About(
            PokemonDetailsUiState.Pokemon(
                "Bublizarre",
                1,
                listOf(Type.GRASS, Type.POISON),
                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png",
                com.orghaniian.data.model.Color.GREEN,
                0.1f,
                2f,
                .7f,
                listOf(
                    PokemonStat(Stat.HP, 45),
                    PokemonStat(Stat.ATTACK, 70)
                )
            )
        )
    }
}