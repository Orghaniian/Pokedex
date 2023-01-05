package com.orghaniian.data.di

import com.orghaniian.data.remote.PokeApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Provides bindings for [Retrofit].
 */
@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {
    private val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BASIC
    }

    private val client = okhttp3.OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()

    /**
     * Provides the [Retrofit] instance.
     */
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://www.pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    /**
     * Provides the [PokeApiService] instance.
     */
    @Provides
    fun providePokeApiService(
        retrofit: Retrofit
    ): PokeApiService {
        return retrofit.create(PokeApiService::class.java)
    }

}
