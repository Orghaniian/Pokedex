package com.orghaniian.pokedex.ui.utils

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.orghaniian.pokedex.R
import com.orghaniian.pokedex.data.model.Type
import com.orghaniian.pokedex.data.model.Type.*

fun Type.getIconDrawable(context: Context): Drawable? = when(this) {
    NORMAL -> R.drawable.normal
    FIGHTING -> R.drawable.fighting
    FLYING -> R.drawable.flying
    POISON -> R.drawable.poison
    GROUND -> R.drawable.ground
    ROCK -> R.drawable.rock
    BUG -> R.drawable.bug
    GHOST -> R.drawable.ghost
    STEEL -> R.drawable.steel
    FIRE -> R.drawable.fire
    WATER -> R.drawable.water
    GRASS -> R.drawable.grass
    ELECTRIC -> R.drawable.electric
    PSYCHIC -> R.drawable.psychic
    ICE -> R.drawable.ice
    DRAGON -> R.drawable.dragon
    DARK -> R.drawable.dark
    FAIRY -> R.drawable.fairy
    UNKNOWN -> R.drawable.unknown
    SHADOW -> R.drawable.dark
}.let { drawableId ->
    return ContextCompat.getDrawable(context, drawableId)
}