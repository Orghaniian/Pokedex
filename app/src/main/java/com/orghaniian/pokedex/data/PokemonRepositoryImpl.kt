package com.orghaniian.pokedex.data

import com.orghaniian.pokedex.data.local.PokemonLocalDataSource
import com.orghaniian.pokedex.data.model.Pokemon
import com.orghaniian.pokedex.data.remote.PokemonRemoteDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PokemonRepositoryImpl @Inject constructor(
    private val pokemonLocalDataSource: PokemonLocalDataSource,
    private val pokemonRemoteDataSource: PokemonRemoteDataSource
) : PokemonRepository {
    override fun getAll(): Flow<List<Pokemon>> = pokemonLocalDataSource.getAll().also {
        CoroutineScope(Dispatchers.IO).launch {
            if (it.first().isEmpty()) {
                pokemonRemoteDataSource.getPokemon().collect { newPokemon ->
                    pokemonLocalDataSource.insert(newPokemon)
                }
            }
        }
    }

}
