package com.orghaniian.pokedex.ui.pokedex

import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.*
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.orghaniian.pokedex.R
import com.orghaniian.pokedex.ui.components.TopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Pokedex(
    navigateToDetails: (Int) -> Unit,
    viewModel: PokedexViewModel = hiltViewModel(),
    onBackPressed: (() -> Unit)? = null
) {
    val pokemons = viewModel.pagingData.collectAsLazyPagingItems()

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                title = stringResource(R.string.app_name),
                onBackPressed = onBackPressed,
                scrollBehavior = scrollBehavior
            )
        },
    ) { paddingValues ->
        PokemonGrid(
            pokemons,
            onPokemonClick = { pokemon ->
                navigateToDetails(pokemon.order)
            },
            modifier = Modifier.padding(paddingValues)
        )
    }
}
