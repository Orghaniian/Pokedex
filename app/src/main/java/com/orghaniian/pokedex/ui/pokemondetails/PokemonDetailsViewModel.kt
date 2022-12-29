package com.orghaniian.pokedex.ui.pokemondetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.orghaniian.pokedex.data.PokemonRepository
import com.orghaniian.pokedex.data.local.PokemonDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    repository: PokemonRepository
) : ViewModel() {

    lateinit var uiState: StateFlow<PokemonDetailsUiState>
        private set

    init {
        viewModelScope.launch {
            val order = PokemonDetailsFragmentArgs.fromSavedStateHandle(savedStateHandle).order
            uiState = repository.getPokemon(order).map { it.toPokemonDetailsUiState() }.stateIn(viewModelScope)
        }
    }

    private fun PokemonDetails.toPokemonDetailsUiState(): PokemonDetailsUiState {
        return PokemonDetailsUiState(
            name,
            order,
            types,
            spriteUrl,
            color
        )
    }
}
