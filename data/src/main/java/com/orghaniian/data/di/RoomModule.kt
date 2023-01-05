package com.orghaniian.data.di

import android.content.Context
import androidx.room.Room
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
    ): com.orghaniian.data.local.AppDatabase {
        return Room.databaseBuilder(
            applicationContext,
            com.orghaniian.data.local.AppDatabase::class.java,
            "pokedex"
        ).build()
    }


    @Provides
    fun providePokemonDao(
        applicationDatabase: com.orghaniian.data.local.AppDatabase
    ): com.orghaniian.data.local.PokemonDao {
        return applicationDatabase.pokemonDao()
    }

}
