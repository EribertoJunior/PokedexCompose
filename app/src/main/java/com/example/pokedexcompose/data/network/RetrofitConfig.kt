package com.example.pokedexcompose.data.network

import android.content.Context
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitConfig(private val baseUrl: String, private val context: Context?) {

    private fun getRetroInstance() = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(OkHttpProvider.getOkHttpClient(context))
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getPokeServide(): PokemonService = getRetroInstance().create(PokemonService::class.java)
}