package com.orghaniian.pokedex.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

/**
 * List of screens for [PokedexApp]
 */
sealed class Screen(val route: String) {
    object Pokedex : Screen("pokedex")
    object Details : Screen("details/{pokemonId}") {
        fun createRoute(pokemonId: Int) = "details/$pokemonId"
    }
}

@Composable
fun rememberPokedexAppState(
    navController: NavHostController = rememberNavController()
) = remember(navController) {
    PokedexAppState(navController)
}

class PokedexAppState(
    val navController: NavHostController,
) {
    fun navigateToPokemonDetails(pokemonId: Int, from: NavBackStackEntry) {
        // In order to discard duplicated navigation events, we check the Lifecycle
        if (from.lifecycleIsResumed()) {
            navController.navigate(Screen.Details.createRoute(pokemonId))
        }
    }

    fun navigateBack() {
        navController.popBackStack()
    }
}

/**
 * If the lifecycle is not resumed it means this NavBackStackEntry already processed a nav event.
 *
 * This is used to de-duplicate navigation events.
 */
private fun NavBackStackEntry.lifecycleIsResumed() =
    this.lifecycle.currentState == Lifecycle.State.RESUMED