package com.orghaniian.pokedex.ui.pokemondetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.orghaniian.pokedex.data.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    repository: PokemonRepository
) : ViewModel() {

    init {
        viewModelScope.launch {
            val order = PokemonDetailsFragmentArgs.fromSavedStateHandle(savedStateHandle).order
            repository.getPokemon(order)
        }
    }
}
