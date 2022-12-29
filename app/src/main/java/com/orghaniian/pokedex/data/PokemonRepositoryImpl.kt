package com.orghaniian.pokedex.data

import com.orghaniian.pokedex.data.model.Pokemon
import com.orghaniian.pokedex.data.remote.PokemonRemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PokemonRepositoryImpl @Inject constructor(
    private val pokemonRemoteDataSource: PokemonRemoteDataSource
) : PokemonRepository {
    override fun getAll(offset: Int, limit: Int): Flow<Pokemon> =
        pokemonRemoteDataSource.getPokemon(limit, offset)
}
