package com.orghaniian.pokedex.ui.pokedex

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.orghaniian.pokedex.R
import com.orghaniian.pokedex.ui.components.TopBar

@Composable
fun Pokedex(
    navigateToDetails: (Int) -> Unit,
    viewModel: PokedexViewModel = hiltViewModel(),
    onBackPressed: (() -> Unit)? = null
) {
    val pokemons = viewModel.pagingData.collectAsLazyPagingItems()

    Column {
        TopBar(title = stringResource(R.string.app_name), onBackPressed = onBackPressed)
        PokemonGrid(
            pokemons,
            onPokemonClick = { pokemon ->
                navigateToDetails(pokemon.order)
            },
        )
    }
}