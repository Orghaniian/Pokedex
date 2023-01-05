package com.orghaniian.pokedex.ui.utils

import com.orghaniian.pokedex.R
import com.orghaniian.data.model.Color

val Color.colorResourceID: Int
    get() = when (this) {
        Color.BLACK -> R.color.black
        Color.BLUE -> R.color.blue
        Color.BROWN -> R.color.brown
        Color.GRAY -> R.color.gray
        Color.GREEN -> R.color.green
        Color.PINK -> R.color.pink
        Color.PURPLE -> R.color.purple
        Color.RED -> R.color.red
        Color.WHITE -> R.color.white
        Color.YELLOW -> R.color.yellow
    }