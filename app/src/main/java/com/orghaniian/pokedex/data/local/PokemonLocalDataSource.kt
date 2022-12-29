package com.orghaniian.pokedex.data.local

import javax.inject.Inject

class PokemonLocalDataSource @Inject constructor(
    private val pokemonDao: PokemonDao
) {
    fun insertAll(pokemons: List<Pokemon>) = pokemonDao.insertAll(pokemons)

    suspend fun getCount() = pokemonDao.getCount()

    fun getPagingSource() = pokemonDao.getPagingSource()

    suspend fun getPokemonDetails(order: Int) = pokemonDao.getPokemonDetails(order)

    fun insert(pokemon: PokemonDetails) = pokemonDao.insert(pokemon)

}
