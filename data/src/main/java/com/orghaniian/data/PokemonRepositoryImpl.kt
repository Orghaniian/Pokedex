package com.orghaniian.data

import androidx.core.os.LocaleListCompat
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.orghaniian.data.local.Pokemon
import com.orghaniian.data.local.PokemonLocalDataSource
import com.orghaniian.data.paging.PokemonRemoteMediator
import com.orghaniian.data.remote.PokemonRemoteDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@OptIn(ExperimentalPagingApi::class)
internal class PokemonRepositoryImpl @Inject constructor(
    private val pokemonLocalDataSource: PokemonLocalDataSource,
    private val pokemonRemoteDataSource: PokemonRemoteDataSource
) : PokemonRepository {

    override fun getPagingData(config: PagingConfig, locales: LocaleListCompat): Flow<PagingData<Pokemon>> {
        return Pager(
            config,
            null,
            PokemonRemoteMediator(pokemonRemoteDataSource, pokemonLocalDataSource, locales)
        ){
            pokemonLocalDataSource.getPagingSource()
        }.flow
    }

    override suspend fun getPokemon(order: Int, locales: LocaleListCompat): Pokemon {
        var pokemonDetails = pokemonLocalDataSource.getPokemon(order)
        if (pokemonDetails == null) {
            pokemonDetails = pokemonRemoteDataSource.getPokemon(locales, order)
            CoroutineScope(Dispatchers.IO).launch {
                pokemonLocalDataSource.insert(pokemonDetails)
            }
        }
        return pokemonDetails
    }
}
