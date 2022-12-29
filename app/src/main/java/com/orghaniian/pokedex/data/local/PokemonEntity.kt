package com.orghaniian.pokedex.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.orghaniian.pokedex.data.model.Color
import com.orghaniian.pokedex.data.model.Type

@Entity(tableName = "pokemons")
data class PokemonEntity(
    @PrimaryKey val order: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "types") val types: List<Type>,
    @ColumnInfo(name = "color") val color: Color,
    @ColumnInfo(name = "sprite_url") val spriteUrl: String
)
