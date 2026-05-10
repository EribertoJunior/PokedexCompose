package com.example.pokedexcompose.data.network.model

import com.example.pokedexcompose.extensions.getUrlId
import com.example.pokedexcompose.data.local.entities.EvolutionChainAddress
import com.example.pokedexcompose.data.local.entities.FlavorTextEntries
import com.example.pokedexcompose.data.local.entities.Language
import com.example.pokedexcompose.data.local.entities.PokemonSpeciesEntity
import com.example.pokedexcompose.data.local.entities.Version
import com.google.gson.annotations.SerializedName

data class SpeciesRemote(
    @SerializedName("id") val id: Int,
    @SerializedName("evolution_chain") val evolutionChainAddressRemote: EvolutionChainAddressRemote?,
    @SerializedName("flavor_text_entries") val flavorTextEntreyRemotes: List<FlavorTextEntreiesRemote>
) {
    fun mapToPokemonSpecie(): PokemonSpeciesEntity {
        return PokemonSpeciesEntity(
            pokemonSpeciesId = id,
            pokemonOwnerId = id,
            evolutionChainAddress = EvolutionChainAddress(url = evolutionChainAddressRemote?.url),
            flavorTextEntries = flavorTextEntreyRemotes
                .filter { it.languageRemote.name == "en" }
                .map {
                    FlavorTextEntries(
                        flavorText = it.flavorText,
                        version = Version(it.versionRemote.name),
                        language = Language(it.languageRemote.name)
                    )

                }
                .first(),
            pokemonSpeciesEvolutionChainId = evolutionChainAddressRemote?.url?.getUrlId ?: 0
        )
    }
}

data class EvolutionChainAddressRemote(
    @SerializedName("url") val url: String
)

data class FlavorTextEntreiesRemote(
    @SerializedName("flavor_text") val flavorText: String,
    @SerializedName("version") val versionRemote: VersionRemote,
    @SerializedName("language") val languageRemote: LanguageRemote
)

data class LanguageRemote(
    @SerializedName("name") val name: String
)

data class VersionRemote(
    @SerializedName("name") val name: String
)