package com.orghaniian.data.local

import androidx.room.TypeConverter
import com.orghaniian.data.model.Stat
import com.orghaniian.data.model.Type

 class Converters {

    @TypeConverter
    fun toListOfTypes(value: String): List<Type> = value.split(",").map {
        Type.valueOf(it)
    }

    @TypeConverter
    fun toStoredTypeString(value: List<Type>): String = value.map(Type::name).joinToString(",")

    @TypeConverter
    fun toListOfPokemonStats(value: String): List<PokemonStat> = value.split(";").map {
        val properties = it.split(",")
        PokemonStat(
            stat = Stat.valueOf(properties[0]),
            value = properties[1].toInt()
        )
    }

    @TypeConverter
    fun toStoredPokemonStatsString(value: List<PokemonStat>): String = value.joinToString(";") {
        "${it.stat},${it.value}"
    }
 }
