package com.orghaniian.pokedex.ui.pokemonlist

import com.orghaniian.pokedex.data.PokemonRepository
import com.orghaniian.pokedex.data.model.Color.BLUE
import com.orghaniian.pokedex.data.model.Color.BROWN
import com.orghaniian.pokedex.data.model.Color.GREEN
import com.orghaniian.pokedex.data.model.Color.PURPLE
import com.orghaniian.pokedex.data.model.Color.RED
import com.orghaniian.pokedex.data.model.Color.WHITE
import com.orghaniian.pokedex.data.model.Color.YELLOW
import com.orghaniian.pokedex.data.model.Pokemon
import com.orghaniian.pokedex.data.model.Type.BUG
import com.orghaniian.pokedex.data.model.Type.FIRE
import com.orghaniian.pokedex.data.model.Type.FLYING
import com.orghaniian.pokedex.data.model.Type.GRASS
import com.orghaniian.pokedex.data.model.Type.NORMAL
import com.orghaniian.pokedex.data.model.Type.POISON
import com.orghaniian.pokedex.data.model.Type.WATER
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
internal class PokemonListViewModelTest {
    private val pokemons = listOf(
        Pokemon(
            "bulbasaur",
            1,
            listOf(GRASS, POISON),
            GREEN,
        ),
        Pokemon(
            "ivysaur",
            2,
            listOf(GRASS, POISON),
            GREEN,
        ),
        Pokemon(
            "venusaur",
            3,
            listOf(GRASS, POISON),
            GREEN,
        ),
        Pokemon(
            "charmander",
            4,
            listOf(FIRE),
            RED,
        ),
        Pokemon(
            "charmeleon",
            5,
            listOf(FIRE),
            RED,
        ),
        Pokemon(
            "charizard",
            6,
            listOf(FIRE, FLYING),
            RED,
        ),
        Pokemon(
            "squirtle",
            7,
            listOf(WATER),
            BLUE,
        ),
        Pokemon(
            "wartortle",
            8,
            listOf(WATER),
            BLUE,
        ),
        Pokemon(
            "blastoise",
            9,
            listOf(WATER),
            BLUE,
        ),
        Pokemon(
            "caterpie",
            10,
            listOf(BUG),
            GREEN,
        ),
        Pokemon(
            "metapod",
            11,
            listOf(BUG),
            GREEN,
        ),
        Pokemon(
            "butterfree",
            12,
            listOf(BUG, FLYING),
            WHITE
        ),
        Pokemon(
            "weedle",
            13,
            listOf(BUG, POISON),
            BROWN,
        ),
        Pokemon(
            "kakuna",
            14,
            listOf(BUG, POISON),
            YELLOW,
        ),
        Pokemon(
            "beedrill",
            15,
            listOf(BUG, POISON),
            YELLOW,
        ),
        Pokemon(
            "pidgey",
            16,
            listOf(NORMAL, FLYING),
            BROWN,
        ),
        Pokemon(
            "pidgeotto",
            17,
            listOf(NORMAL, FLYING),
            BROWN,
        ),
        Pokemon(
            "pidgeot",
            18,
            listOf(NORMAL, FLYING),
            BROWN,
        ),
        Pokemon(
            "rattata",
            19,
            listOf(NORMAL),
            PURPLE,
        ),
        Pokemon(
            "raticate",
            20,
            listOf(NORMAL),
            BROWN,
        ),
    )

    private val fakeRepository = object: PokemonRepository {
        override fun getAll(offset: Int, limit: Int) = flow {
            pokemons.indices.forEach { index ->
                emit(pokemons.subList(0, index))
            }
        }
    }

    @Test
    fun fetchPokemons() = runTest(dispatchTimeoutMs = 10_000L) {
        val expected = pokemons.map {
            PokemonListItemUiState(
                it.name,
                it.order,
                it.types,
                it.spriteUrl,
                it.color
            )
        }

        val viewModel = PokemonListViewModel(fakeRepository)
        var index = 0
        viewModel.uiState.onEach {
            assertEquals(PokemonListUiState(expected.subList(0, index++)), it)
        }
    }
}
