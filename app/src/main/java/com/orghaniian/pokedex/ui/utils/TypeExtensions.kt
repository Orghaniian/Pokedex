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

val Type.stringResourceID: Int
    get() = when (this) {
        NORMAL -> R.string.type_normal
        FIGHTING -> R.string.type_fighting
        FLYING -> R.string.type_flying
        POISON -> R.string.type_poison
        GROUND -> R.string.type_ground
        ROCK -> R.string.type_rock
        BUG -> R.string.type_bug
        GHOST -> R.string.type_ghost
        STEEL -> R.string.type_steel
        FIRE -> R.string.type_fire
        WATER -> R.string.type_water
        GRASS -> R.string.type_grass
        ELECTRIC -> R.string.type_electric
        PSYCHIC -> R.string.type_psychic
        ICE -> R.string.type_ice
        DRAGON -> R.string.type_dragon
        DARK -> R.string.type_dark
        FAIRY -> R.string.type_fairy
        UNKNOWN -> R.string.type_unknown
        SHADOW -> R.string.type_shadow
    }