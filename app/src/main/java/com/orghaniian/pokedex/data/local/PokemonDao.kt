package com.orghaniian.pokedex.data.local

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
    fun insert(pokemon: PokemonDetails)

    @Query("SELECT COUNT(*) FROM pokemons")
    suspend fun getCount(): Int

    @Query("SELECT * FROM pokemons")
    fun getPagingSource(): PagingSource<Int, Pokemon>

    @Query("SELECT * FROM pokemons_details WHERE pokedex_order=:order")
    suspend fun getPokemonDetails(order: Int): PokemonDetails?
}
