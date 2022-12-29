package com.orghaniian.pokedex.data

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.orghaniian.pokedex.data.local.Pokemon
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    fun getPagingData(config: PagingConfig): Flow<PagingData<Pokemon>>
}
