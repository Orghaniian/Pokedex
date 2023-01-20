package com.orghaniian.pokedex.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.orghaniian.pokedex.ui.pokemondetails.PokemonDetailsRoute
import com.orghaniian.pokedex.ui.pokedex.Pokedex

@Composable
fun PokedexApp(
    appState: PokedexAppState = rememberPokedexAppState()
) {
    NavHost(
        navController = appState.navController,
        startDestination = Screen.Pokedex.route
    ) {
        composable(Screen.Pokedex.route) { backStackEntry ->
            Pokedex(
                navigateToDetails = { pokemonId ->
                    appState.navigateToPokemonDetails(pokemonId, backStackEntry)
                }
            )
        }
        composable(Screen.Details.route) { _ ->
            PokemonDetailsRoute()
        }
    }
}