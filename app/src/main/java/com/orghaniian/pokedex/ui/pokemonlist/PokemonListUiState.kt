package com.orghaniian.pokedex.ui.pokemonlist

import com.orghaniian.pokedex.data.model.Color
import com.orghaniian.pokedex.data.model.Type

data class PokemonListUiState(
    val pokemons: List<PokemonListItemUiState> = emptyList()
)

data class PokemonListItemUiState(
    val name: String,
    val order: Int,
    val types: List<Type>,
    val spriteUrl: String,
    val color: Color
)
