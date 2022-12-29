package com.orghaniian.pokedex.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.orghaniian.pokedex.data.local.Pokemon
import com.orghaniian.pokedex.data.local.PokemonDetails
import com.orghaniian.pokedex.data.local.PokemonLocalDataSource
import com.orghaniian.pokedex.data.paging.PokemonRemoteMediator
import com.orghaniian.pokedex.data.remote.PokemonRemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@OptIn(ExperimentalPagingApi::class)
class PokemonRepositoryImpl @Inject constructor(
    private val pokemonLocalDataSource: PokemonLocalDataSource,
    private val pokemonRemoteDataSource: PokemonRemoteDataSource
) : PokemonRepository {

    override fun getPagingData(config: PagingConfig): Flow<PagingData<Pokemon>> {
        return Pager(
            config,
            null,
            PokemonRemoteMediator(pokemonRemoteDataSource, pokemonLocalDataSource)
        ){
            pokemonLocalDataSource.getPagingSource()
        }.flow
    }

    override fun getPokemon(order: Int): Flow<PokemonDetails> {
        return pokemonLocalDataSource.getPokemon(order)
    }
}
