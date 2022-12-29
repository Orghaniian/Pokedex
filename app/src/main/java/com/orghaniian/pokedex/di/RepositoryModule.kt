package com.orghaniian.pokedex.di

import com.orghaniian.pokedex.data.PokemonRepository
import com.orghaniian.pokedex.data.PokemonRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindPokemonRepositoryt(pokemonRepositoryImpl: PokemonRepositoryImpl): PokemonRepository
}
