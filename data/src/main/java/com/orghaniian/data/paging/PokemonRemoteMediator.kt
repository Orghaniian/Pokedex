package com.orghaniian.data.paging

import androidx.core.os.LocaleListCompat
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.paging.RemoteMediator.InitializeAction.SKIP_INITIAL_REFRESH
import com.orghaniian.data.local.Pokemon
import com.orghaniian.data.local.PokemonLocalDataSource
import com.orghaniian.data.remote.PokemonRemoteDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException
import java.util.*

@OptIn(ExperimentalPagingApi::class)
 internal class PokemonRemoteMediator(
    private val pokemonRemoteDataSource: PokemonRemoteDataSource,
    private val pokemonLocalDataSource: PokemonLocalDataSource,
    private val locales: LocaleListCompat
): RemoteMediator<Int, Pokemon>() {

    override suspend fun initialize(): InitializeAction = SKIP_INITIAL_REFRESH

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Pokemon>
    ): MediatorResult {
        return try {
            state.anchorPosition
            val offset = when (loadType) {
                LoadType.REFRESH -> 0
                LoadType.PREPEND -> return MediatorResult.Success(true)
                LoadType.APPEND -> pokemonLocalDataSource.getCount()
            }

            val response = pokemonRemoteDataSource.getAllPokemon(locales, state.config.pageSize, offset)

            CoroutineScope(Dispatchers.IO).launch {
                pokemonLocalDataSource.insertAll(response.results)
            }

            MediatorResult.Success(!response.next)
        } catch (e: IOException) {
            MediatorResult.Error(e)
        }
    }
}
