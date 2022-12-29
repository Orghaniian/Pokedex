package com.orghaniian.pokedex.ui.pokemonlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.orghaniian.pokedex.data.PokemonRepository
import com.orghaniian.pokedex.data.model.Pokemon
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val repository: PokemonRepository
): ViewModel() {
    private val _uiState = MutableStateFlow(PokemonListUiState())
    val uiState: StateFlow<PokemonListUiState> = _uiState.asStateFlow()

    private var fetchJob: Job? = null

    fun fetchPokemons(offset: Int = 0, limit: Int = 20) {
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch {
            repository.getAll(offset, limit).map{ it.toListOfPokemonListItemUiState() }.collect { pokemons ->
                _uiState.update {
                    it.copy(pokemons = pokemons)
                }
            }
        }
    }

    private fun List<Pokemon>.toListOfPokemonListItemUiState() = map{ it.toPokemonListItemUiState() }

    private fun Pokemon.toPokemonListItemUiState() = PokemonListItemUiState(
        name,
        order,
        types,
        spriteUrl,
        color
    )
}
