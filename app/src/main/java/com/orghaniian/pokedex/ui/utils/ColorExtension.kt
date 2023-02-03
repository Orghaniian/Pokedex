package com.orghaniian.pokedex.ui.utils

import com.orghaniian.data.model.Color
import com.orghaniian.pokedex.ui.theme.PokedexColor

val Color.value: androidx.compose.ui.graphics.Color
    get() = when (this) {
        Color.BLACK -> PokedexColor.black
        Color.BLUE -> PokedexColor.blue
        Color.BROWN -> PokedexColor.brown
        Color.GRAY -> PokedexColor.gray
        Color.GREEN -> PokedexColor.green
        Color.PINK -> PokedexColor.pink
        Color.PURPLE -> PokedexColor.purple
        Color.RED -> PokedexColor.red
        Color.WHITE -> PokedexColor.white
        Color.YELLOW -> PokedexColor.yellow
    }