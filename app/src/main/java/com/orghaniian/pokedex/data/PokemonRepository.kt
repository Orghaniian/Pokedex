package com.orghaniian.pokedex.data

import com.orghaniian.pokedex.data.model.Pokemon
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    //fun get(id: Int): Pokemon
    //fun get(name: String): Pokemon
    fun getAll(): Flow<List<Pokemon>>
}
