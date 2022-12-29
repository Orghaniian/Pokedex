package com.orghaniian.pokedex.data.local

import javax.inject.Inject

class PokemonLocalDataSource @Inject constructor(
    private val pokemonDao: PokemonDao
) {
    suspend fun insertAll(pokemons: List<Pokemon>) = pokemonDao.insertAll(pokemons)

    suspend fun getCount() = pokemonDao.getCount()

    fun getPagingSource() = pokemonDao.getPagingSource()

}
