package com.orghaniian.pokedex.data.model

data class Pokemon(
    val name: String,
    val order: Int,
    val types: List<Type>,
    val color: Color,
    val spriteUrl: String = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$order.png" // todo Changer ?
)
