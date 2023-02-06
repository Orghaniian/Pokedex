package com.orghaniian.data.remote

import androidx.core.os.LocaleListCompat
import com.orghaniian.data.local.Pokemon
import com.orghaniian.data.model.Stat
import com.orghaniian.data.model.Type
import com.orghaniian.data.remote.model.ColorResource
import com.orghaniian.data.remote.model.GetAllResponse
import com.orghaniian.data.remote.model.PokemonStat
import com.orghaniian.data.remote.model.PokemonType
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

internal class PokemonRemoteDataSource @Inject constructor(private val pokeApi: PokeApi) {
    suspend fun getAllPokemon(locales: LocaleListCompat, limit: Int? = null, offset: Int? = null) = pokeApi.run {
        val apiResponse = getPokemon(limit, offset)

        val pokemons = coroutineScope {
            apiResponse.results.map { (name, _) ->
                async { fetchPokemon(name, locales) }
            }.awaitAll()
        }

        return@run GetAllResponse(
            apiResponse.count,
            apiResponse.next != null,
            apiResponse.previous != null,
            pokemons
        )
    }

    suspend fun getPokemon(locales: LocaleListCompat, order: Int) = pokeApi.run { fetchPokemon(order.toString(), locales) }

    private suspend fun PokeApi.fetchPokemon(name: String, locales: LocaleListCompat): Pokemon {
        val pokemon = getPokemon(name)
        val species = getPokemonSpecies(name)

        val availableLocalesForName = species.names.map { it.language.name }
        val localeForName = locales.getFirstMatch(availableLocalesForName.toTypedArray())

        return Pokemon(
            pokemon.id,
            species.names.firstOrNull { it.language.name == localeForName?.language }?.name ?: pokemon.name,
            pokemon.types.toListOfType(),
            species.color.toColor(),
            pokemon.sprites.other.officialArtwork.frontDefault,
            (8f - species.genderRate)/8f,
            pokemon.height / 100f,
            pokemon.weight / 10f,
            pokemon.stats.toListOfStat()
        )
    }
}

private fun List<PokemonType>.toListOfType() = map { Type.valueOf(it.type.name.uppercase()) }
private fun List<PokemonStat>.toListOfStat() = map {
    com.orghaniian.data.local.PokemonStat(
        Stat.valueOf(it.stat.name.replace("-", "_").uppercase()),
        it.baseState
    )
}
private fun ColorResource.toColor() = com.orghaniian.data.model.Color.valueOf(name.uppercase())
