package com.orghaniian.pokedex.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.orghaniian.pokedex.ui.pokemondetails.PokemonDetails
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
        composable(Screen.Details.route) {
            PokemonDetails(
                onBackPressed = {
                    if(!appState.navController.popBackStack()) {
                        appState.navController.navigate(Screen.Pokedex.route)
                    }
                }
            )
        }
    }
}