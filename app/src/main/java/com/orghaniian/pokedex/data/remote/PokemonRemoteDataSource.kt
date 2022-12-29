package com.orghaniian.pokedex.data.remote

import com.orghaniian.pokedex.data.model.Color
import com.orghaniian.pokedex.data.model.Pokemon
import com.orghaniian.pokedex.data.model.Type
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PokemonRemoteDataSource @Inject constructor(private val pokeApiService: PokeApiService) {

    fun getPokemon(limit: Int? = 151, offset: Int? = null): Flow<Pokemon> = flow {
        pokeApiService.run {
            getPokemon(limit, offset).results.forEach { (name, _) ->
                val pokemon = getPokemon(name)
                val species = getPokemonSpecies(name)

                emit(
                    Pokemon(
                        name = pokemon.name,
                        order = pokemon.id,
                        types = pokemon.types.map { Type.valueOf(it.type.name.uppercase()) },
                        color = Color.valueOf(species.color.name.uppercase()),
                        spriteUrl = pokemon.sprites.other.officialArtwork.frontDefault
                    )
                )
            }
        }
    }
}
