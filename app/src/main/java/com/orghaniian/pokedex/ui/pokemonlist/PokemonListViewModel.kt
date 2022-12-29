package com.orghaniian.pokedex.ui.pokemonlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.map
import com.orghaniian.pokedex.data.PokemonRepository
import com.orghaniian.pokedex.data.local.Pokemon
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    repository: PokemonRepository
): ViewModel() {

    val pagingData = repository.getPagingData(PagingConfig(PAGE_SIZE)).map { pagingData ->
        pagingData.map { it.toPokemonListItemUiState() }
    }.cachedIn(viewModelScope)


    private fun Pokemon.toPokemonListItemUiState() = PokemonListItemUiState(
        name,
        order,
        types,
        spriteUrl,
        color
    )

    companion object {
        private const val PAGE_SIZE = 20
    }
}
