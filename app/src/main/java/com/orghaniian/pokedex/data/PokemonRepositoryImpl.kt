package com.orghaniian.pokedex.data

import com.orghaniian.pokedex.data.model.Pokemon
import com.orghaniian.pokedex.data.remote.PokemonRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PokemonRepositoryImpl @Inject constructor(
    private val pokemonRemoteDataSource: PokemonRemoteDataSource
) : PokemonRepository {
    override fun getAll(offset: Int, limit: Int): Flow<List<Pokemon>> = flow {
        val pokemons = mutableListOf<Pokemon>()
        pokemonRemoteDataSource.getPokemon(limit, offset).collect { newPokemon ->
            emit(pokemons.also { it.add(newPokemon) }.toList())
        }
    }

}
