package com.orghaniian.pokedex.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.paging.RemoteMediator.InitializeAction.SKIP_INITIAL_REFRESH
import com.orghaniian.pokedex.data.local.Pokemon
import com.orghaniian.pokedex.data.local.PokemonLocalDataSource
import com.orghaniian.pokedex.data.remote.PokemonRemoteDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class PokemonRemoteMediator(
    private val pokemonRemoteDataSource: PokemonRemoteDataSource,
    private val pokemonLocalDataSource: PokemonLocalDataSource
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

            val response = pokemonRemoteDataSource.getAllPokemon(state.config.pageSize, offset)

            CoroutineScope(Dispatchers.IO).launch {
                pokemonLocalDataSource.insertAll(response.results)
            }

            MediatorResult.Success(!response.next)
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}
