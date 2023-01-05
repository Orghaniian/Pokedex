package com.orghaniian.pokedex.ui.pokemonlist

import com.orghaniian.data.model.Color
import com.orghaniian.data.model.Type

data class PokemonListItemUiState(
    val name: String,
    val order: Int,
    val types: List<Type>,
    val spriteUrl: String,
    val color: Color
)
