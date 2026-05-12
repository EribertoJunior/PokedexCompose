package com.example.pokedexcompose.data.network.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class MappersRemoteTest {

    @Test
    fun `EvolutionChainRemote mapToEvolutionChain should correctly map nested structure`() {
        // Given
        val remote = EvolutionChainRemote(
            id = 1,
            chain = ChainRemote(
                species = SpecieToEvolutionRemote(name = "pichu", url = "https://pokeapi.co/api/v2/pokemon-species/172/"),
                evolvesTo = listOf(
                    ChainRemote(
                        species = SpecieToEvolutionRemote(name = "pikachu", url = "https://pokeapi.co/api/v2/pokemon-species/25/"),
                        evolvesTo = listOf(
                            ChainRemote(
                                species = SpecieToEvolutionRemote(name = "raichu", url = "https://pokeapi.co/api/v2/pokemon-species/26/"),
                                evolvesTo = emptyList()
                            )
                        )
                    )
                )
            )
        )

        // When
        val entity = remote.mapToEvolutionChain()

        // Then
        assertEquals(1, entity.evolutionChainId)
        assertEquals(3, entity.evolutionList.size)
        assertEquals("pichu", entity.evolutionList[0].name)
        assertEquals("pikachu", entity.evolutionList[1].name)
        assertEquals("raichu", entity.evolutionList[2].name)
        assertTrue(entity.evolutionList[0].imageUrl.contains("172.png"))
        assertTrue(entity.evolutionList[1].imageUrl.contains("25.png"))
        assertTrue(entity.evolutionList[2].imageUrl.contains("26.png"))
    }

    @Test
    fun `SpeciesRemote mapToPokemonSpecie should correctly map filters and data`() {
        // Given
        val remote = SpeciesRemote(
            id = 1,
            evolutionChainAddressRemote = EvolutionChainAddressRemote(url = "https://pokeapi.co/api/v2/evolution-chain/1/"),
            flavorTextEntreyRemotes = listOf(
                FlavorTextEntreiesRemote(
                    flavorText = "English text",
                    versionRemote = VersionRemote("red"),
                    languageRemote = LanguageRemote("en")
                ),
                FlavorTextEntreiesRemote(
                    flavorText = "Texto em Português",
                    versionRemote = VersionRemote("red"),
                    languageRemote = LanguageRemote("pt")
                )
            )
        )

        // When
        val entity = remote.mapToPokemonSpecie()

        // Then
        assertEquals(1, entity.pokemonSpeciesEvolutionChainId)
        assertEquals("English text", entity.flavorTextEntries.flavorText)
        assertEquals("en", entity.flavorTextEntries.language.name)
    }

    @Test
    fun `PokemonDetailRemote mapPokeDetailRemoteToPokeDetail should map all fields`() {
        // Given
        val remote = PokemonDetailRemote(
            id = 1,
            name = "bulbasaur",
            weight = 69,
            height = 7,
            types = listOf(
                DataTypesRemote(slot = 1, type = TypeRemote("grass")),
                DataTypesRemote(slot = 2, type = TypeRemote("poison"))
            ),
            sprites = SpritesRemote(
                other = OtherRemote(
                    officialArtwork = OfficialArtworkRemote(frontDefault = "artwork_url"),
                    home = HomeRemote(frontDefault = "home_url")
                )
            ),
            species = PokemonDetailRemoteSpecies(name = "bulbasaur", url = "specie_url"),
            stats = listOf(
                PokemonDetailRemoteStats(baseStat = 45, effort = 0, stat = StatRemote("hp", "url"))
            )
        )

        // When
        val entity = remote.mapPokeDetailRemoteToPokeDetail()

        // Then
        assertEquals(1, entity.pokemonDetailId)
        assertEquals(1, entity.pokemonOwnerId)
        assertEquals(69, entity.weight)
        assertEquals(7, entity.height)
        assertEquals(2, entity.colorTypeList.size)
        assertEquals("GRASS", entity.colorTypeList[0].name)
        assertEquals("artwork_url", entity.sprites?.other?.officialArtwork?.frontDefault)
        assertEquals("home_url", entity.sprites?.other?.home?.frontDefault)
        assertEquals("bulbasaur", entity.species?.name)
        assertEquals(1, entity.stats.size)
        assertEquals("hp", entity.stats[0].stat.name)
    }
}
