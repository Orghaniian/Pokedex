package com.orghaniian.data.remote

import com.orghaniian.data.remote.model.NamedAPIResourceList
import com.orghaniian.data.remote.model.Pokemon
import com.orghaniian.data.remote.model.PokemonSpecies
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeApiService {

    @GET("pokemon")
    suspend fun getPokemon(
        @Query("limit") limit: Int? = null,
        @Query("offset") offset: Int? = null
    ) : NamedAPIResourceList

    @GET("pokemon/{id}")
    suspend fun getPokemon(@Path("id") id: Int) : Pokemon

    @GET("pokemon/{name}")
    suspend fun getPokemon(@Path("name") name: String) : Pokemon

    @GET("pokemon-species/{id}")
    suspend fun getPokemonSpecies(@Path("id") id: Int) : PokemonSpecies

    @GET("pokemon-species/{name}")
    suspend fun getPokemonSpecies(@Path("name") name: String) : PokemonSpecies
}
