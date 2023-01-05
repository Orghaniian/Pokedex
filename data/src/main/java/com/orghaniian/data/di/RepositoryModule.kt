package com.orghaniian.data.di

import com.orghaniian.data.PokemonRepository
import com.orghaniian.data.PokemonRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindPokemonRepositoryt(pokemonRepositoryImpl: PokemonRepositoryImpl): PokemonRepository
}
