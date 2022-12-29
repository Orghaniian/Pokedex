package com.orghaniian.pokedex.data.model

import java.util.*

enum class Type{
    NORMAL,
    FIGHTING,
    FLYING,
    POISON,
    GROUND,
    ROCK,
    BUG,
    GHOST,
    STEEL,
    FIRE,
    WATER,
    GRASS,
    ELECTRIC,
    PSYCHIC,
    ICE,
    DRAGON,
    DARK,
    FAIRY,
    UNKNOWN,
    SHADOW;

    fun localizedName(locale: Locale?): String = when(locale?.language){
        "fr" -> {
            when(this){
                NORMAL -> "Normal"
                FIGHTING -> "Combat"
                FLYING -> "Vol"
                POISON -> "Poison"
                GROUND -> "Sol"
                ROCK -> "Roche"
                BUG -> "Insecte"
                GHOST -> "Spectre"
                STEEL -> "Acier"
                FIRE -> "Feu"
                WATER -> "Eau"
                GRASS -> "Plante"
                ELECTRIC -> "Électrik"
                PSYCHIC -> "Psy"
                ICE -> "Glace"
                DRAGON -> "Dragon"
                DARK -> "Ténèbres"
                FAIRY -> "Fée"
                UNKNOWN -> "Inconnu"
                SHADOW -> "Ombre"
            }
        }
        else -> {
            this.name
        }
    }
}
