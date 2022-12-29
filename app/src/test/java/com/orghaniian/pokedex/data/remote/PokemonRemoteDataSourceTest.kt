package com.orghaniian.pokedex.data.remote

import com.orghaniian.pokedex.data.model.Color
import com.orghaniian.pokedex.data.model.Pokemon
import com.orghaniian.pokedex.data.model.Type
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@OptIn(ExperimentalCoroutinesApi::class)
internal class PokemonRemoteDataSourceTest {
    private val remoteDataSource = PokemonRemoteDataSource(
        Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PokeApiService::class.java)
    )

    private val expectedPokemons = listOf(
        Pokemon(
            "bulbasaur",
            1,
            listOf(Type.GRASS, Type.POISON),
            Color.GREEN,
        ),
        Pokemon(
            "ivysaur",
            2,
            listOf(Type.GRASS, Type.POISON),
            Color.GREEN,
        ),
        Pokemon(
            "venusaur",
            3,
            listOf(Type.GRASS, Type.POISON),
            Color.GREEN,
        ),
        Pokemon(
            "charmander",
            4,
            listOf(Type.FIRE),
            Color.RED,
        ),
        Pokemon(
            "charmeleon",
            5,
            listOf(Type.FIRE),
            Color.RED,
        ),
        Pokemon(
            "charizard",
            6,
            listOf(Type.FIRE, Type.FLYING),
            Color.RED,
        ),
        Pokemon(
            "squirtle",
            7,
            listOf(Type.WATER),
            Color.BLUE,
        ),
        Pokemon(
            "wartortle",
            8,
            listOf(Type.WATER),
            Color.BLUE,
        ),
        Pokemon(
            "blastoise",
            9,
            listOf(Type.WATER),
            Color.BLUE,
        ),
        Pokemon(
            "caterpie",
            10,
            listOf(Type.BUG),
            Color.GREEN,
        ),
        Pokemon(
            "metapod",
            11,
            listOf(Type.BUG),
            Color.GREEN,
        ),
        Pokemon(
            "butterfree",
            12,
            listOf(Type.BUG, Type.FLYING),
            Color.WHITE
        ),
        Pokemon(
            "weedle",
            13,
            listOf(Type.BUG, Type.POISON),
            Color.BROWN,
        ),
        Pokemon(
            "kakuna",
            14,
            listOf(Type.BUG, Type.POISON),
            Color.YELLOW,
        ),
        Pokemon(
            "beedrill",
            15,
            listOf(Type.BUG, Type.POISON),
            Color.YELLOW,
        ),
        Pokemon(
            "pidgey",
            16,
            listOf(Type.NORMAL, Type.FLYING),
            Color.BROWN,
        ),
        Pokemon(
            "pidgeotto",
            17,
            listOf(Type.NORMAL, Type.FLYING),
            Color.BROWN,
        ),
        Pokemon(
            "pidgeot",
            18,
            listOf(Type.NORMAL, Type.FLYING),
            Color.BROWN,
        ),
        Pokemon(
            "rattata",
            19,
            listOf(Type.NORMAL),
            Color.PURPLE,
        ),
        Pokemon(
            "raticate",
            20,
            listOf(Type.NORMAL),
            Color.BROWN,
        ),
    )

    @Test
    fun testGetPokemon() = runTest {
        var index = 0
        remoteDataSource.getPokemon().onEach {
            assertEquals(expectedPokemons[index++], it)
        }
    }
}
