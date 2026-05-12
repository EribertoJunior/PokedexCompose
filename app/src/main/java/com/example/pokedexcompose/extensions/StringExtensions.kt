package com.example.pokedexcompose.extensions

import androidx.compose.ui.graphics.Color
import java.util.Locale

val String.color: Color
    get() = try {
        Color(android.graphics.Color.parseColor(this))
    } catch (e: Exception) {
        // Fallback for Unit Tests where android.graphics.Color is not mocked
        if (this.startsWith("#")) {
            val colorInt = java.lang.Long.parseLong(this.substring(1), 16).toInt()
            if (this.length == 7) Color(colorInt or -0x1000000) else Color(colorInt)
        } else Color.Gray
    }

val String.getUrlId
    get() = run {
        val regex = "/\\d+".toRegex()
        regex.find(this)?.value?.replace("/", "")?.toInt() ?: 0
    }

val String.titlecase
    get() = run {
        replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
    }
