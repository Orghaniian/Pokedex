package com.orghaniian.pokedex.data

import com.orghaniian.pokedex.data.model.Pokemon

interface PokemonRepository {
    fun get(id: Int): Pokemon
    fun get(name: String): Pokemon
    fun getAll(offset: Int = 0, limit: Int = 20): List<Pokemon>
}