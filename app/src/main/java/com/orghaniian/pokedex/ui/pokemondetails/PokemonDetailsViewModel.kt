package com.orghaniian.pokedex.ui.pokemondetails

import androidx.annotation.StringRes
import androidx.core.os.LocaleListCompat
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.orghaniian.data.PokemonRepository
import com.orghaniian.data.local.Pokemon
import com.orghaniian.data.local.PokemonStat
import com.orghaniian.data.model.Color
import com.orghaniian.data.model.Type
import com.orghaniian.pokedex.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    repository: PokemonRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(
        PokemonDetailsUiState(
            tabs = PokemonDetailsUiState.Tab.values().asList()
        )
    )

    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            val order = savedStateHandle.get<String>("pokemonId")!!.toInt()
            _uiState.update {
                it.copy(
                    pokemon = repository.getPokemon(order, LocaleListCompat.getAdjustedDefault()).toUiStatePokemon()
                )
            }
        }
    }

    fun selectTab(tab: PokemonDetailsUiState.Tab): Unit = _uiState.update { it.copy(currentTab = tab) }

    private fun Pokemon.toUiStatePokemon(): PokemonDetailsUiState.Pokemon {
        return PokemonDetailsUiState.Pokemon(
            name,
            order,
            types,
            spriteUrl,
            color,
            height,
            weight,
            genderRate,
            stats
        )
    }
}

data class PokemonDetailsUiState(
    val pokemon: Pokemon? = null,
    val currentTab: Tab = Tab.About,
    val tabs: List<Tab> = emptyList()
) {
    data class Pokemon(
        val name: String,
        val order: Int,
        val types: List<Type>,
        val spriteUrl: String,
        val color: Color,
        val height: Float,
        val weight: Float,
        val genderRate: Float,
        val stats: List<PokemonStat>
    )

    enum class Tab(@StringRes val titleId: Int) {
        About(R.string.about_name),
        BaseStats(R.string.base_stats_name),
        Evolution(R.string.evolution_name)
    }
}
