package com.example.pokedexcompose.extensions

import java.util.Locale

fun Int.toDoubleFormat(decimals: Int): String {
    return String.format(Locale.US, "%.${decimals}f", this.toDouble() / 10)
}