package com.orghaniian.pokedex.data.remote

import com.orghaniian.pokedex.data.local.Pokemon
import com.orghaniian.pokedex.data.model.Color
import com.orghaniian.pokedex.data.model.Type
import com.orghaniian.pokedex.data.remote.model.GetAllResponse
import javax.inject.Inject

class PokemonRemoteDataSource @Inject constructor(private val pokeApiService: PokeApiService) {
    suspend fun getAllPokemon(limit: Int? = null, offset: Int? = null) = pokeApiService.run {
        val apiResponse = getPokemon(limit, offset)

        val pokemons = apiResponse.results.map { (name, _) ->
            fetchPokemon(name)
        }

        return@run GetAllResponse(
            apiResponse.count,
            apiResponse.next != null,
            apiResponse.previous != null,
            pokemons
        )
    }

    private suspend fun PokeApiService.fetchPokemon(name: String): Pokemon {
        val pokemon = getPokemon(name)
        val species = getPokemonSpecies(name)

        return Pokemon(
            name = pokemon.name,
            order = pokemon.id,
            types = pokemon.types.map { Type.valueOf(it.type.name.uppercase()) },
            color = Color.valueOf(species.color.name.uppercase()),
            spriteUrl = pokemon.sprites.other.officialArtwork.frontDefault
        )
    }
}
