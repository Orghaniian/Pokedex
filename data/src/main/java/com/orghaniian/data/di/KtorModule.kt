package com.orghaniian.data.di

import com.orghaniian.data.remote.PokeApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import javax.inject.Singleton

/**
 * Provides bindings for [HttpClient].
 */
@Module
@InstallIn(SingletonComponent::class)
internal object KtorModule {
    private val client : HttpClient = HttpClient(OkHttp) {
        defaultRequest {
            url("https://www.pokeapi.co/api/v2/")
        }
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
    }

    @Singleton
    @get:Provides
    val api : PokeApi = PokeApi(client)
}
