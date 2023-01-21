package com.orghaniian.pokedex.ui.pokedex

import androidx.core.os.LocaleListCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.map
import com.orghaniian.data.PokemonRepository
import com.orghaniian.data.local.Pokemon
import com.orghaniian.data.model.Color
import com.orghaniian.data.model.Type
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class PokedexViewModel @Inject constructor(
    repository: PokemonRepository
): ViewModel() {
    private var lastScrollIndex = 0

    private val _scrollUp = MutableStateFlow(false)
    val scrollUp: StateFlow<Boolean>
        get() = _scrollUp

    val pagingData = repository.getPagingData(PagingConfig(PAGE_SIZE), LocaleListCompat.getAdjustedDefault()).map { pagingData ->
        pagingData.map { it.toPokedexItemUiState() }
    }.cachedIn(viewModelScope)

    fun updateScrollPosition(newScrollIndex: Int) {
        if (newScrollIndex == lastScrollIndex) return

        _scrollUp.value = newScrollIndex > lastScrollIndex
        lastScrollIndex = newScrollIndex
    }

    private fun Pokemon.toPokedexItemUiState() = PokedexItemUiState(
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

data class PokedexItemUiState(
    val name: String,
    val order: Int,
    val types: List<Type>,
    val spriteUrl: String,
    val color: Color
)
