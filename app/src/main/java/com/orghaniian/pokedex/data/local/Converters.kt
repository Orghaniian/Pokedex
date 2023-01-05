package com.orghaniian.pokedex.data.local

import androidx.room.TypeConverter
import com.orghaniian.pokedex.data.model.Type

class Converters {

    @TypeConverter
    fun toListOfTypes(value: String): List<Type> = value.split(",").map {
        Type.valueOf(it)
    }

    @TypeConverter
    fun toStoredString(value: List<Type>): String = value.map(Type::name).joinToString(",")
}
