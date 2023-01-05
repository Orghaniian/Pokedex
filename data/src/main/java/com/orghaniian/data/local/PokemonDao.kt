package com.orghaniian.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonDao {
    @Query("SELECT * FROM pokemons")
    fun getAll(): Flow<List<Pokemon>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(pokemons: List<Pokemon>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(pokemon: Pokemon)

    @Query("SELECT COUNT(*) FROM pokemons")
    suspend fun getCount(): Int

    @Query("SELECT * FROM pokemons")
    fun getPagingSource(): PagingSource<Int, Pokemon>

    @Query("SELECT * FROM pokemons WHERE pokedex_order=:order")
    suspend fun getPokemon(order: Int): Pokemon?
}
