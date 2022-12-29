package com.orghaniian.pokedex.data

import androidx.core.os.LocaleListCompat
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.orghaniian.pokedex.data.local.Pokemon
import com.orghaniian.pokedex.data.local.PokemonDetails
import com.orghaniian.pokedex.data.local.PokemonLocalDataSource
import com.orghaniian.pokedex.data.paging.PokemonRemoteMediator
import com.orghaniian.pokedex.data.remote.PokemonRemoteDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@OptIn(ExperimentalPagingApi::class)
class PokemonRepositoryImpl @Inject constructor(
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

    override suspend fun getPokemonDetails(order: Int): PokemonDetails {
        var pokemonDetails = pokemonLocalDataSource.getPokemonDetails(order)
        if (pokemonDetails == null) {
            pokemonDetails = pokemonRemoteDataSource.getPokemonDetails(order)
            CoroutineScope(Dispatchers.IO).launch {
                pokemonLocalDataSource.insert(pokemonDetails)
            }
        }
        return pokemonDetails
    }
}
