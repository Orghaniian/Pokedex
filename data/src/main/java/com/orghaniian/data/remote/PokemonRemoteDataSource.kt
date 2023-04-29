package com.orghaniian.data.remote

import androidx.core.os.LocaleListCompat
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.Optional
import com.orghaniian.data.CountPokemonQuery
import com.orghaniian.data.ListPokemonQuery
import com.orghaniian.data.ListPokemonQuery.Pokemon_v2_pokemon
import com.orghaniian.data.local.Pokemon
import com.orghaniian.data.model.Stat
import com.orghaniian.data.model.Type
import com.orghaniian.data.remote.model.GetAllResponse
import com.orghaniian.data.type.Int_comparison_exp
import com.orghaniian.data.type.Pokemon_v2_pokemon_bool_exp
import javax.inject.Inject

internal class PokemonRemoteDataSource @Inject constructor(private val apolloClient: ApolloClient) {

    private var total: Int? = null

    private suspend fun getCount(): Int {
        return total ?: apolloClient
            .query(CountPokemonQuery())
            .execute()
            .dataAssertNoErrors
            .pokemon_v2_pokemon_aggregate
            .aggregate
            ?.count
            ?.also { total = it }
        ?: throw Exception("Unable to retrieve the total amount of pokemons")
    }

    suspend fun getAllPokemon(locales: LocaleListCompat, limit: Int = 20, offset: Int = 0): GetAllResponse<Pokemon> {

        val data = apolloClient.query(
            ListPokemonQuery(
                limit.opt(),
                offset.opt(),
            )
        ).execute().dataAssertNoErrors

        val responses = data.pokemon_v2_pokemon.map { it.toModel(locales) }

        val next = (getCount() - (offset + limit)) > 0

        return GetAllResponse(
            responses.size,
            next,
            offset > 0,
            responses
        )
    }

    suspend fun getPokemon(locales: LocaleListCompat, order: Int): Pokemon {
        val data = apolloClient.query(
            ListPokemonQuery(
                where = Pokemon_v2_pokemon_bool_exp(order = Int_comparison_exp(_eq = order.opt()).opt()).opt()
            )
        ).execute().dataAssertNoErrors

        return data.pokemon_v2_pokemon.single().toModel(locales)
    }
}

private fun Pokemon_v2_pokemon.toModel(locales: LocaleListCompat): Pokemon = Pokemon(
    order = id,
    name = nameForLocales(locales),
    types = types,
    color = color,
    genderRate = genderRate,
    height = height?.div(100f) ?: throw Exception("Missing height for pokemon $id"),
    weight = weight?.div(10f) ?: throw Exception("Missing weight for pokemon $id"),
    stats = stats
)

private fun Pokemon_v2_pokemon.nameForLocales(locales: LocaleListCompat): String {
    val names: Map<String?, String>? = pokemon_v2_pokemonspecy
        ?.pokemon_v2_pokemonspecies
        ?.firstOrNull()
        ?.pokemon_v2_pokemonspeciesnames
        ?.associateBy({ it.pokemon_v2_language?.iso639 }, { it.name })
        ?.filter { it.key != null }

    val localeForName = names?.let { locales.getFirstMatch(names.keys.toTypedArray()) }?.language
    return localeForName?.let { names[localeForName] } ?: name
}

private val Pokemon_v2_pokemon.types get() = pokemon_v2_pokemontypes.mapNotNull { it.pokemon_v2_type?.name }.map { Type.valueOf(it.uppercase()) }
private val Pokemon_v2_pokemon.color get() = pokemon_v2_pokemonspecy
    ?.pokemon_v2_pokemoncolor
    ?.name
    ?.uppercase()
    ?.let { com.orghaniian.data.model.Color.valueOf(it) }
    ?: throw Exception("Missing color for pokemon $id#$name")

private val Pokemon_v2_pokemon.genderRate get() = pokemon_v2_pokemonspecy
    ?.gender_rate
    ?.let { computeGenderRate(it) }
    ?: throw Exception("Missing gender rate for pokemon $id")

private val Pokemon_v2_pokemon.stats get() = pokemon_v2_pokemonstats.map { stat ->
    com.orghaniian.data.local.PokemonStat(
        stat.pokemon_v2_stat
            ?.let { Stat.valueOf(it.name.replace("-", "_").uppercase()) }
            ?: throw Exception("Missing stat name for pokemon $id"),
        stat.base_stat
    )
}

private fun computeGenderRate(genderRate: Int) = (8f - genderRate)/8f

private fun <V> V?.opt(): Optional<V> = this?.let{ Optional.present(it) } ?: Optional.absent()
