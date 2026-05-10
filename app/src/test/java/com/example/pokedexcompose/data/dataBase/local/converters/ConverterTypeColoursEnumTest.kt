package com.example.pokedexcompose.data.dataBase.local.converters

import com.example.pokedexcompose.data.local.converters.ConverterTypeColoursEnum
import com.example.pokedexcompose.data.local.enums.TypeColoursEnum
import io.mockk.spyk
import org.junit.Assert.*
import org.junit.Test

class ConverterTypeColoursEnumTest {

    private var converterTypeColoursEnum = spyk(ConverterTypeColoursEnum())

    @Test
    fun `should return a jsonArray when a list of TypeColoursEnum is passed`() {

        val jsonArray = "[\"DRAGON\",\"FIRE\"]"
        val listOf = listOf(
            TypeColoursEnum.DRAGON,
            TypeColoursEnum.FIRE
        )

        run {
            val listString = converterTypeColoursEnum.saveListEnumToListString(listOf)
            assertEquals(jsonArray, listString)
        }
    }

    @Test
    fun `should return a list of TypeColoursEnum when a jsonArray is passed`() {

        val jsonArray = "[\"DRAGON\",\"FIRE\"]"

        val listOf = listOf(
            TypeColoursEnum.DRAGON,
            TypeColoursEnum.FIRE,
        )

        run {
            val list = converterTypeColoursEnum.restoreListEnum(jsonArray)
            assertEquals(listOf, list)
        }
    }
}