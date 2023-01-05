package com.orghaniian.data

import androidx.core.os.LocaleListCompat
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.orghaniian.data.local.Pokemon
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    fun getPagingData(config: PagingConfig, locales: LocaleListCompat): Flow<PagingData<Pokemon>>

    suspend fun getPokemon(order: Int, locales: LocaleListCompat): Pokemon
}
