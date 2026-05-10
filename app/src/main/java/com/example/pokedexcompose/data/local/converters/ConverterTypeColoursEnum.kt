package com.example.pokedexcompose.data.local.converters

import androidx.room.TypeConverter
import com.example.pokedexcompose.data.local.enums.TypeColoursEnum
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ConverterTypeColoursEnum {

    @TypeConverter
    fun restoreListEnum(typeColoursEnumNames: String): List<TypeColoursEnum> {
        return Gson().fromJson(
            typeColoursEnumNames,
            object : TypeToken<List<TypeColoursEnum>>() {}.type
        )
    }

    @TypeConverter
    fun saveListEnumToListString(typeColoursEnums: List<TypeColoursEnum>): String {
        return Gson().toJson(typeColoursEnums)
    }
}