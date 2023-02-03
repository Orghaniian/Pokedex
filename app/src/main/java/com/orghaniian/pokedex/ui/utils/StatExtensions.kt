package com.orghaniian.pokedex.ui.utils

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import com.orghaniian.data.model.Stat
import com.orghaniian.data.model.Stat.*
import com.orghaniian.pokedex.R
import com.orghaniian.pokedex.ui.theme.PokedexColor

@get:StringRes
val Stat.stringResourceId: Int
    get() = when(this) {
        ATTACK -> R.string.attack
        HP -> R.string.hp
        DEFENSE -> R.string.defense
        SPECIAL_ATTACK -> R.string.special_attack
        SPECIAL_DEFENSE -> R.string.special_defense
        SPEED -> R.string.speed
        ACCURACY -> R.string.accuracy
        EVASION -> R.string.evasion
    }

val Stat.color: Color
    get() = when(this) {
        ATTACK -> PokedexColor.purple
        HP -> PokedexColor.red
        DEFENSE -> PokedexColor.brown
        SPECIAL_ATTACK -> PokedexColor.purple
        SPECIAL_DEFENSE -> PokedexColor.brown
        SPEED -> PokedexColor.yellow
        ACCURACY -> PokedexColor.blue
        EVASION -> PokedexColor.green
    }