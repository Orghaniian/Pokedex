package com.orghaniian.pokedex.ui.pokemondetails

import com.orghaniian.data.model.Color
import com.orghaniian.data.model.Type

data class PokemonDetailsUiState(
    val name: String,
    val order: Int,
    val types: List<Type>,
    val spriteUrl: String,
    val color: Color,
    val height: Float,
    val weight: Float,
    val genderRate: Float
)
