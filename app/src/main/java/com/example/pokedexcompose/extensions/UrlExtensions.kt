package com.example.pokedexcompose.extensions

fun String.getOffsetFromUrl(): Int? {
    val regex = "[?&]offset=(\\d+)".toRegex()
    return regex.find(this)?.groupValues?.get(1)?.toInt()
}
