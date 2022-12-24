package com.orghaniian.pokedex.ui.pokemonlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.orghaniian.pokedex.data.PokemonRepository
import com.orghaniian.pokedex.data.model.Color
import com.orghaniian.pokedex.data.model.Pokemon
import com.orghaniian.pokedex.data.model.Type
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PokemonListViewModel(private val repository: PokemonRepository = object : PokemonRepository {
    private val pokemons = listOf(
        Pokemon("bulbasaur", 1, listOf(Type.GRASS, Type.POISON), Color.GREEN),
        Pokemon("ivysaur", 2, listOf(Type.GRASS, Type.POISON), Color.GREEN),
        Pokemon("venusaur", 3, listOf(Type.GRASS, Type.POISON), Color.GREEN),
        Pokemon("charmander", 4, listOf(Type.FIRE), Color.RED),
        Pokemon("charmeleon", 5, listOf(Type.FIRE), Color.RED),
        Pokemon("charizard", 6, listOf(Type.FIRE, Type.FLYING), Color.RED),
        Pokemon("squirtle", 7, listOf(Type.WATER), Color.BLUE),
        Pokemon("wartortle", 8, listOf(Type.WATER), Color.BLUE),
        Pokemon("blastoise", 9, listOf(Type.WATER), Color.BLUE),
        Pokemon("caterpie", 10, listOf(Type.BUG), Color.GREEN),
        Pokemon("metapod", 11, listOf(Type.BUG), Color.GREEN),
        Pokemon("butterfree", 12, listOf(Type.BUG, Type.FLYING), Color.GREEN),
        Pokemon("weedle", 13, listOf(Type.BUG, Type.POISON), Color.GREEN),
        Pokemon("kakuna", 14, listOf(Type.BUG, Type.POISON), Color.GREEN),
        Pokemon("beedrill", 15, listOf(Type.BUG, Type.POISON), Color.GREEN),
        Pokemon("pidgey", 16, listOf(Type.NORMAL, Type.FLYING), Color.BROWN),
        Pokemon("pidgeotto", 17, listOf(Type.NORMAL, Type.FLYING), Color.BROWN),
        Pokemon("pidgeot", 18, listOf(Type.NORMAL, Type.FLYING), Color.BROWN),
        Pokemon("rattata", 19, listOf(Type.NORMAL), Color.BROWN),
        Pokemon("raticate", 20, listOf(Type.NORMAL, Type.DARK), Color.BROWN),
        Pokemon("spearow", 21, listOf(Type.NORMAL, Type.FLYING), Color.BROWN),
        Pokemon("fearow", 22, listOf(Type.NORMAL, Type.FLYING), Color.BROWN),
        Pokemon("ekans", 23, listOf(Type.POISON), Color.PURPLE),
        Pokemon("arbok", 24, listOf(Type.POISON), Color.PURPLE),
        Pokemon("pikachu", 25, listOf(Type.ELECTRIC), Color.YELLOW),
        Pokemon("raichu", 26, listOf(Type.ELECTRIC), Color.YELLOW),
        Pokemon("sandshrew", 27, listOf(Type.GROUND), Color.BROWN),
        Pokemon("sandslash", 28, listOf(Type.GROUND), Color.BROWN),
        Pokemon("nidoran♀", 29, listOf(Type.POISON), Color.PURPLE),
        Pokemon("nidorina", 30, listOf(Type.POISON), Color.PURPLE)
    )

    override fun get(id: Int): Pokemon = pokemons[id]

    override fun get(name: String): Pokemon = pokemons.first { it.name == name }

    override fun getAll(offset: Int, limit: Int): List<Pokemon> = pokemons.subList(offset, offset + limit)

}): ViewModel() {
    private val _uiState = MutableStateFlow(PokemonListUiState())
    val uiState: StateFlow<PokemonListUiState> = _uiState.asStateFlow()

    private var fetchJob: Job? = null

    fun fetchPokemons(offset: Int = 0, limit: Int = 20) {
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch {
            val pokemons = repository.getAll(offset, limit)
            pokemons.map{ it.toPokemonListItemUiState() }.let { newPokemons ->
                _uiState.update {
                    PokemonListUiState(newPokemons)
                }
            }
        }
    }

    private fun Pokemon.toPokemonListItemUiState() = PokemonListItemUiState(
        name,
        order,
        types,
        spriteUrl,
        color
    )
}