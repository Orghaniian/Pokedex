package com.orghaniian.data.remote

import com.orghaniian.data.remote.model.NamedAPIResourceList
import com.orghaniian.data.remote.model.Pokemon
import com.orghaniian.data.remote.model.PokemonSpecies
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

internal class PokeApi(
    private val client: HttpClient
) {
    suspend fun getPokemon(limit: Int?, offset: Int?): NamedAPIResourceList = client.get("pokemon") {
        url {
            limit?.let {  parameters.append("limit", it.toString()) }
            offset?.let {  parameters.append("offset", it.toString()) }
        }
    }.body()

    suspend fun getPokemon(id: Int): Pokemon = client.get("pokemon/$id").body()

    suspend fun getPokemon(name: String): Pokemon = client.get("pokemon/$name").body()

    suspend fun getPokemonSpecies(id: Int): PokemonSpecies = client.get("pokemon-species/$id").body()

    suspend fun getPokemonSpecies(name: String): PokemonSpecies = client.get("pokemon-species/$name").body()
}
