package com.orghaniian.pokedex.ui.pokemondetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.orghaniian.pokedex.data.PokemonRepository
import com.orghaniian.pokedex.data.local.PokemonDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    repository: PokemonRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<PokemonDetailsUiState?>(null)

    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            val order = PokemonDetailsFragmentArgs.fromSavedStateHandle(savedStateHandle).order
            _uiState.emit(repository.getPokemonDetails(order).toPokemonDetailsUiState())
        }
    }

    private fun PokemonDetails.toPokemonDetailsUiState(): PokemonDetailsUiState {
        return PokemonDetailsUiState(
            name,
            order,
            types,
            spriteUrl,
            color,
            height,
            weight,
            genderRate
        )
    }
}
