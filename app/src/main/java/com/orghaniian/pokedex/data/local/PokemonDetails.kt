package com.orghaniian.pokedex.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.orghaniian.pokedex.data.model.Color
import com.orghaniian.pokedex.data.model.Type

//TODO add necessary attributes
@Entity(tableName = "pokemons_details")
data class PokemonDetails(
    @PrimaryKey @ColumnInfo(name = "pokedex_order") val order: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "types") val types: List<Type>,
    @ColumnInfo(name = "color") val color: Color,
    @ColumnInfo(name = "sprite_url") val spriteUrl: String = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$order.png"
)

