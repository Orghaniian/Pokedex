package com.orghaniian.pokedex.data.local

import javax.inject.Inject

class PokemonLocalDataSource @Inject constructor(
    private val pokemonDao: PokemonDao
) {
    fun insertAll(pokemons: List<Pokemon>) = pokemonDao.insertAll(pokemons)

    suspend fun getCount() = pokemonDao.getCount()

    fun getPagingSource() = pokemonDao.getPagingSource()

    suspend fun getPokemon(order: Int) = pokemonDao.getPokemon(order)

    fun insert(pokemon: Pokemon) = pokemonDao.insert(pokemon)

}
