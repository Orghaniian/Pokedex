package com.orghaniian.pokedex.ui.pokedex

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems

@Composable
fun Pokedex(
    navigateToDetails: (Int) -> Unit,
    viewModel: PokedexViewModel = hiltViewModel(),
) {
    val pokemons = viewModel.pagingData.collectAsLazyPagingItems()

    PokemonGrid(
        pokemons,
        onPokemonClick = { pokemon ->
            navigateToDetails(pokemon.order)
        },
    )
}