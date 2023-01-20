package com.orghaniian.pokedex.ui.pokemondetails.moves

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.orghaniian.data.model.Color
import com.orghaniian.data.model.Type
import com.orghaniian.pokedex.ui.pokemondetails.PokemonDetailsUiState

@Composable
fun Moves(
    pokemon: PokemonDetailsUiState.Pokemon
) {
    Text("Moves")
}

@Preview
@Composable
private fun PreviewMoves(){
    MaterialTheme {
        Moves(
            PokemonDetailsUiState.Pokemon(
                "Bublizarre",
                1,
                listOf(Type.GRASS, Type.POISON),
                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png",
                Color.GREEN,
                0.1f,
                2f,
                .7f
            )
        )
    }
}