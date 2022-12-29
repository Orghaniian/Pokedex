package com.orghaniian.pokedex.data

import androidx.core.os.LocaleListCompat
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.orghaniian.pokedex.data.local.Pokemon
import com.orghaniian.pokedex.data.local.PokemonDetails
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    fun getPagingData(config: PagingConfig, locales: LocaleListCompat): Flow<PagingData<Pokemon>>

    suspend fun getPokemonDetails(order: Int): PokemonDetails
}
