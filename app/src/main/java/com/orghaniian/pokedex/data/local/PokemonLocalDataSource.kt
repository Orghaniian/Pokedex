package com.orghaniian.pokedex.data.local

import com.orghaniian.pokedex.data.model.Pokemon
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PokemonLocalDataSource @Inject constructor(
    private val pokemonDao: PokemonDao
) {
    fun getAll(): Flow<List<Pokemon>> = pokemonDao.getAll().map { it.toListOfPokemon() }

    suspend fun insertAll(pokemons: List<Pokemon>) = pokemonDao.insertAll(pokemons.toListOfPokemonEntity())

    suspend fun insert(pokemon: Pokemon) = pokemonDao.insert(pokemon.toPokemonEntity())

    private fun PokemonEntity.toPokemon(): Pokemon = Pokemon(
        name,
        order,
        types,
        color,
        spriteUrl
    )

    private fun List<PokemonEntity>.toListOfPokemon() = map { it.toPokemon() }

    private fun Pokemon.toPokemonEntity(): PokemonEntity = PokemonEntity(
        order,
        name,
        types,
        color,
        spriteUrl
    )

    private fun List<Pokemon>.toListOfPokemonEntity() = map { it.toPokemonEntity() }

}
