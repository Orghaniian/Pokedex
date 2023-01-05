package com.orghaniian.pokedex.ui.pokemonlist

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.orghaniian.data.local.Pokemon
import com.orghaniian.data.model.Color
import com.orghaniian.data.model.Type
import com.orghaniian.pokedex.R
import com.orghaniian.pokedex.ui.utils.colorResourceID

@Composable
fun PokemonCard(pokemon: Pokemon) {
    Surface (
        color = colorResource(pokemon.color.colorResourceID),
        contentColor = colorResource(R.color.on_type),
        shape = RoundedCornerShape(dimensionResource(R.dimen.corner_radius)),
        modifier = Modifier.fillMaxSize().padding(dimensionResource(R.dimen.pokemon_list_item_padding))
    ) {
        Text(pokemon.name)
    }
}

@Preview
@Composable
private fun PokemonCardPreview() {
    MaterialTheme {
        PokemonCard(
            Pokemon(
                1,
                "bulbizarre",
                listOf(Type.GRASS, Type.POISON),
                Color.GREEN,
                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png",
                .875f,
                .7f,
                .4f
            )
        )
    }
}