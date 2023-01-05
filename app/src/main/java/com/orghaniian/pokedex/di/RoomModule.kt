package com.orghaniian.pokedex.di

import android.content.Context
import androidx.room.Room
import com.orghaniian.pokedex.data.local.AppDatabase
import com.orghaniian.pokedex.data.local.PokemonDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun provideAppDatabase(
        @ApplicationContext applicationContext: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "pokedex"
        ).build()
    }


    @Provides
    fun providePokemonDao(
        applicationDatabase: AppDatabase
    ): PokemonDao {
        return applicationDatabase.pokemonDao()
    }

}
