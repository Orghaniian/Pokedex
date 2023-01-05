package com.orghaniian.pokedex.data.model

import com.orghaniian.pokedex.R

enum class Type(val stringResourceID: Int) {
    NORMAL(R.string.type_normal),
    FIGHTING(R.string.type_fighting),
    FLYING(R.string.type_flying),
    POISON(R.string.type_poison),
    GROUND(R.string.type_ground),
    ROCK(R.string.type_rock),
    BUG(R.string.type_bug),
    GHOST(R.string.type_ghost),
    STEEL(R.string.type_steel),
    FIRE(R.string.type_fire),
    WATER(R.string.type_water),
    GRASS(R.string.type_grass),
    ELECTRIC(R.string.type_electric),
    PSYCHIC(R.string.type_psychic),
    ICE(R.string.type_ice),
    DRAGON(R.string.type_dragon),
    DARK(R.string.type_dark),
    FAIRY(R.string.type_fairy),
    UNKNOWN(R.string.type_unknown),
    SHADOW(R.string.type_shadow);
}
