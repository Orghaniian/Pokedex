package com.orghaniian.pokedex.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.orghaniian.pokedex.data.model.Color
import com.orghaniian.pokedex.data.model.Type

/**
 * @property genderRate probability of this pokemon being a male, from 0 to 1, -1 if pokemon is genderless
 * @property height of the pokemon in meters
 * @property weight of the pokemon in kilograms
 */
@Entity(tableName = "pokemons_details")
data class PokemonDetails(
    @PrimaryKey @ColumnInfo(name = "pokedex_order") val order: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "types") val types: List<Type>,
    @ColumnInfo(name = "color") val color: Color,
    @ColumnInfo(name = "sprite_url") val spriteUrl: String = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$order.png",
    @ColumnInfo(name = "gender_rate") val genderRate: Float,
    @ColumnInfo(name = "height") val height: Float,
    @ColumnInfo(name = "weight") val weight: Float,
)

