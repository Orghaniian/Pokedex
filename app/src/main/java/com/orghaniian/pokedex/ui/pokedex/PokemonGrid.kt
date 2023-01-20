package com.orghaniian.pokedex.ui.pokedex

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.orghaniian.data.model.Color
import com.orghaniian.data.model.Type
import com.orghaniian.pokedex.R

@Composable
fun PokemonGrid(
    pokemons: LazyPagingItems<PokedexItemUiState>,
    onPokemonClick: (PokedexItemUiState) -> Unit,
    modifier: Modifier = Modifier
) = CustomGrid(
    modifier = modifier
) {
    items(
        count = pokemons.itemCount,
        key = { index -> index }
    ) {index ->
        pokemons[index]?.let {
            PokemonCard(
                it,
                modifier = Modifier.clickable {
                    onPokemonClick(it)
                }.height(100.dp)
            )
        } ?: SkeletonPokemonCard(modifier = Modifier.height(100.dp))
    }
}

@Composable
private fun CustomGrid(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(16.dp),
    content: LazyGridScope.() -> Unit
) = LazyVerticalGrid(
    verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.margin_m)),
    horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.margin_m)),
    modifier = modifier,
    columns = GridCells.Adaptive(minSize = 128.dp),
    contentPadding = contentPadding,
    content = content
)

@Preview(widthDp = 412, heightDp = 892)
@Composable
private fun PreviewCustomGrid() {
    val pokemons = buildList {
        repeat(16) {
            add(
                PokedexItemUiState(
                    "Bulbizarre",
                    1,
                    listOf(Type.GRASS, Type.POISON),
                    "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png",
                    Color.GREEN,
                )
            )
            add(
                PokedexItemUiState(
                    "Bulbizarre",
                    1,
                    listOf(Type.GRASS, Type.POISON),
                    "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png",
                    Color.GREEN,
                )
            )
            add(
                PokedexItemUiState(
                    "Bulbizarre",
                    1,
                    listOf(Type.GRASS),
                    "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png",
                    Color.RED,
                )
            )
        }
    }
    
    MaterialTheme {
        CustomGrid{
            items(pokemons) {
                PokemonCard(it, modifier = Modifier.height(100.dp))
            }
        }
    }
}