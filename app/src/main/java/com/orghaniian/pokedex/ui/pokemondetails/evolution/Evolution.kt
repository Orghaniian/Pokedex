package com.orghaniian.pokedex.ui.pokemondetails.evolution

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.orghaniian.data.local.PokemonStat
import com.orghaniian.data.model.Color
import com.orghaniian.data.model.Stat
import com.orghaniian.data.model.Type
import com.orghaniian.pokedex.ui.pokemondetails.PokemonDetailsUiState

@Composable
fun Evolution(
    pokemon: PokemonDetailsUiState.Pokemon
) {
    Text("Evolution")
}

@Preview
@Composable
private fun PreviewEvolution(){
    MaterialTheme {
        Evolution(
            PokemonDetailsUiState.Pokemon(
                "Bublizarre",
                1,
                listOf(Type.GRASS, Type.POISON),
                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png",
                Color.GREEN,
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