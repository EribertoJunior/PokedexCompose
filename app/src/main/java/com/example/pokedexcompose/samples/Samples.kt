package com.example.pokedexcompose.samples

import com.example.pokedexcompose.data.local.entities.EvolutionChainEntity
import com.example.pokedexcompose.data.local.entities.EvolutionChainAddress
import com.example.pokedexcompose.data.local.entities.FlavorTextEntries
import com.example.pokedexcompose.data.local.entities.Home
import com.example.pokedexcompose.data.local.entities.Language
import com.example.pokedexcompose.data.local.entities.OfficialArtwork
import com.example.pokedexcompose.data.local.entities.Other
import com.example.pokedexcompose.data.local.entities.PokemonEntity
import com.example.pokedexcompose.data.local.entities.PokemonDetail
import com.example.pokedexcompose.data.local.entities.PokemonDetailSpecies
import com.example.pokedexcompose.data.local.entities.PokemonDetailStats
import com.example.pokedexcompose.data.local.entities.PokemonSpeciesEntity
import com.example.pokedexcompose.data.local.entities.SpecieToEvolution
import com.example.pokedexcompose.data.local.entities.Sprites
import com.example.pokedexcompose.data.local.entities.Stat
import com.example.pokedexcompose.data.local.entities.Version
import com.example.pokedexcompose.data.local.relations.PokemonAndDetail
import com.example.pokedexcompose.data.local.relations.SpecieAndEvolutionChain
import com.example.pokedexcompose.data.local.enums.TypeColoursEnum

private fun setUrlImage(idPokemon: Int) =
    "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${idPokemon}.png"

val listPokemonEntitySample = listOf(
    PokemonAndDetail(
        pokemonEntity = PokemonEntity(
            pokemonId = 0,
            name = "Bulbasaur",
            imageUrl = setUrlImage(1)
        ),
        pokemonDetail = PokemonDetail(
            colorTypeList = listOf(
                TypeColoursEnum.DRAGON,
                TypeColoursEnum.FIRE
            ),
            sprites = Sprites(
                Other(
                    officialArtwork = OfficialArtwork(""),
                    home = Home("")
                )
            ),
            weight = 123,
            height = 123,
            stats = listOf(
                PokemonDetailStats(
                    baseStat = 65,
                    effort = 0,
                    stat = Stat(
                        name = "hp",
                        url = ""
                    )
                ),
                PokemonDetailStats(
                    baseStat = 105,
                    effort = 2,
                    stat = Stat(
                        name = "attack",
                        url = ""
                    )
                ),
                PokemonDetailStats(
                    baseStat = 60,
                    effort = 0,
                    stat = Stat(
                        name = "defense",
                        url = ""
                    )
                ),
            ),
            species = PokemonDetailSpecies(
                name = "",
                url = ""
            )
        ),
    ),

    PokemonAndDetail(
        pokemonEntity = PokemonEntity(
            pokemonId = 1,
            name = "Ivysaur",
            imageUrl = setUrlImage(2)
        ),
        pokemonDetail = PokemonDetail(
            colorTypeList = listOf(
                TypeColoursEnum.DRAGON,
                TypeColoursEnum.FIRE
            ),
            sprites = Sprites(
                Other(
                    officialArtwork = OfficialArtwork(""),
                    home = Home("")
                )
            ),
            weight = 123,
            height = 123,
            stats = listOf(
                PokemonDetailStats(
                    baseStat = 65,
                    effort = 0,
                    stat = Stat(
                        name = "hp",
                        url = ""
                    )
                ),
                PokemonDetailStats(
                    baseStat = 105,
                    effort = 2,
                    stat = Stat(
                        name = "attack",
                        url = ""
                    )
                ),
                PokemonDetailStats(
                    baseStat = 60,
                    effort = 0,
                    stat = Stat(
                        name = "defense",
                        url = ""
                    )
                ),
            ),
            species = PokemonDetailSpecies(
                name = "",
                url = ""
            )
        )
    ),
    PokemonAndDetail(
        pokemonEntity = PokemonEntity(
            pokemonId = 2,
            name = "Venusaur",
            imageUrl = setUrlImage(3)
        ),
        pokemonDetail = PokemonDetail(
            colorTypeList = listOf(
                TypeColoursEnum.DRAGON,
                TypeColoursEnum.FIRE,
            ),
            sprites = Sprites(
                Other(
                    officialArtwork = OfficialArtwork(""),
                    home = Home("")
                )
            ),
            weight = 123,
            height = 123,
            stats = listOf(
                PokemonDetailStats(
                    baseStat = 65,
                    effort = 0,
                    stat = Stat(
                        name = "hp",
                        url = ""
                    )
                ),
                PokemonDetailStats(
                    baseStat = 105,
                    effort = 2,
                    stat = Stat(
                        name = "attack",
                        url = ""
                    )
                ),
                PokemonDetailStats(
                    baseStat = 60,
                    effort = 0,
                    stat = Stat(
                        name = "defense",
                        url = ""
                    )
                ),
            ),
            species = PokemonDetailSpecies(
                name = "",
                url = ""
            )
        )
    ),
    PokemonAndDetail(
        pokemonEntity = PokemonEntity(
            pokemonId = 3,
            name = "Charmander",
            imageUrl = setUrlImage(4)
        ),
        pokemonDetail = PokemonDetail(
            colorTypeList = listOf(
                TypeColoursEnum.DRAGON,
                TypeColoursEnum.FIRE
            ),
            sprites = Sprites(
                Other(
                    officialArtwork = OfficialArtwork(""),
                    home = Home("")
                )
            ),
            weight = 123,
            height = 123,
            stats = listOf(
                PokemonDetailStats(
                    baseStat = 65,
                    effort = 0,
                    stat = Stat(
                        name = "hp",
                        url = ""
                    )
                ),
                PokemonDetailStats(
                    baseStat = 105,
                    effort = 2,
                    stat = Stat(
                        name = "attack",
                        url = ""
                    )
                ),
                PokemonDetailStats(
                    baseStat = 60,
                    effort = 0,
                    stat = Stat(
                        name = "defense",
                        url = ""
                    )
                ),
            ),
            species = PokemonDetailSpecies(
                name = "",
                url = ""
            )
        ),
    ),
    PokemonAndDetail(
        pokemonEntity = PokemonEntity(
            pokemonId = 4,
            name = "Charmeleon",

            imageUrl = setUrlImage(5)
        ),
        pokemonDetail = PokemonDetail(
            colorTypeList = listOf(
                TypeColoursEnum.DRAGON,
                TypeColoursEnum.FIRE
            ),
            sprites = Sprites(
                Other(
                    officialArtwork = OfficialArtwork(""),
                    home = Home("")
                )
            ),
            weight = 123,
            height = 123,
            stats = listOf(
                PokemonDetailStats(
                    baseStat = 65,
                    effort = 0,
                    stat = Stat(
                        name = "hp",
                        url = ""
                    )
                ),
                PokemonDetailStats(
                    baseStat = 105,
                    effort = 2,
                    stat = Stat(
                        name = "attack",
                        url = ""
                    )
                ),
                PokemonDetailStats(
                    baseStat = 60,
                    effort = 0,
                    stat = Stat(
                        name = "defense",
                        url = ""
                    )
                ),
            ),
            species = PokemonDetailSpecies(
                name = "",
                url = ""
            )
        )
    ),
    PokemonAndDetail(
        pokemonEntity = PokemonEntity(
            pokemonId = 5,
            name = "Charizard",
            imageUrl = setUrlImage(6)
        ),
        pokemonDetail = PokemonDetail(
            colorTypeList = listOf(
                TypeColoursEnum.DRAGON,
                TypeColoursEnum.FIRE
            ),
            sprites = Sprites(
                Other(
                    officialArtwork = OfficialArtwork(""),
                    home = Home("")
                )
            ),
            weight = 123,
            height = 123,
            stats = listOf(
                PokemonDetailStats(
                    baseStat = 78,
                    effort = 0,
                    stat = Stat(
                        name = "hp",
                        url = ""
                    )
                ),
                PokemonDetailStats(
                    baseStat = 84,
                    effort = 2,
                    stat = Stat(
                        name = "attack",
                        url = ""
                    )
                ),
                PokemonDetailStats(
                    baseStat = 78,
                    effort = 0,
                    stat = Stat(
                        name = "defense",
                        url = ""
                    )
                ),
                PokemonDetailStats(
                    baseStat = 109,
                    effort = 0,
                    stat = Stat(
                        name = "special-attack",
                        url = ""
                    )
                ),
                PokemonDetailStats(
                    baseStat = 85,
                    effort = 0,
                    stat = Stat(
                        name = "special-defense",
                        url = ""
                    )
                ),
                PokemonDetailStats(
                    baseStat = 100,
                    effort = 0,
                    stat = Stat(
                        name = "speed",
                        url = ""
                    )
                ),
            ),
            species = PokemonDetailSpecies(
                name = "",
                url = ""
            )
        ),
        specieAndEvolutionChain = SpecieAndEvolutionChain(
            pokemonSpeciesEntity = PokemonSpeciesEntity(
                flavorTextEntries = FlavorTextEntries(
                    flavorText = "A CHARIZARD flies about in search of\nstrong opponents. It breathes intense\nflames that can melt any material. However,  \nit will never torch a weaker foe.",
                    version = Version("en"),
                    language = Language("en")
                ),
                evolutionChainAddress = EvolutionChainAddress(""),
                pokemonSpeciesEvolutionChainId = 0
            ),
            evolutionChainEntity = EvolutionChainEntity(
                evolutionChainId = 0,
                evolutionList = listOf(
                    SpecieToEvolution(name = "charmander", imageUrl = ""),
                    SpecieToEvolution(name = "charmeleon", imageUrl = ""),
                    SpecieToEvolution(name = "charizard", imageUrl = ""),
                )
            )
        )

    ),
    PokemonAndDetail(
        pokemonEntity = PokemonEntity(
            pokemonId = 6,
            name = "Squirtle",
            imageUrl = setUrlImage(7)
        ),
        pokemonDetail = PokemonDetail(
            colorTypeList = listOf(
                TypeColoursEnum.DRAGON,
                TypeColoursEnum.FIRE
            ),
            sprites = Sprites(
                Other(
                    officialArtwork = OfficialArtwork(""),
                    home = Home("")
                )
            ),
            weight = 123,
            height = 123,
            stats = listOf(
                PokemonDetailStats(
                    baseStat = 65,
                    effort = 0,
                    stat = Stat(
                        name = "hp",
                        url = ""
                    )
                ),
                PokemonDetailStats(
                    baseStat = 105,
                    effort = 2,
                    stat = Stat(
                        name = "attack",
                        url = ""
                    )
                ),
                PokemonDetailStats(
                    baseStat = 60,
                    effort = 0,
                    stat = Stat(
                        name = "defense",
                        url = ""
                    )
                ),
            ),
            species = PokemonDetailSpecies(
                name = "",
                url = ""
            )
        ),
    ),
    PokemonAndDetail(
        pokemonEntity = PokemonEntity(
            pokemonId = 7,
            name = "Wartortle",
            imageUrl = setUrlImage(8)
        ),
        pokemonDetail = PokemonDetail(
            colorTypeList = listOf(
                TypeColoursEnum.DRAGON,
                TypeColoursEnum.FIRE
            ),
            sprites = Sprites(
                Other(
                    officialArtwork = OfficialArtwork(""),
                    home = Home("")
                )
            ),
            weight = 123,
            height = 123,
            stats = listOf(
                PokemonDetailStats(
                    baseStat = 65,
                    effort = 0,
                    stat = Stat(
                        name = "hp",
                        url = ""
                    )
                ),
                PokemonDetailStats(
                    baseStat = 105,
                    effort = 2,
                    stat = Stat(
                        name = "attack",
                        url = ""
                    )
                ),
                PokemonDetailStats(
                    baseStat = 60,
                    effort = 0,
                    stat = Stat(
                        name = "defense",
                        url = ""
                    )
                ),
            ),
            species = PokemonDetailSpecies(
                name = "",
                url = ""
            )
        ),
    ),
    PokemonAndDetail(
        pokemonEntity = PokemonEntity(
            pokemonId = 8,
            name = "Blastoise",
            imageUrl = setUrlImage(9)
        ),
        pokemonDetail = PokemonDetail(
            colorTypeList = listOf(
                TypeColoursEnum.DRAGON,
                TypeColoursEnum.FIRE
            ),
            sprites = Sprites(
                Other(
                    officialArtwork = OfficialArtwork(""),
                    home = Home("")
                )
            ),
            weight = 123,
            height = 123,
            stats = listOf(
                PokemonDetailStats(
                    baseStat = 65,
                    effort = 0,
                    stat = Stat(
                        name = "hp",
                        url = ""
                    )
                ),
                PokemonDetailStats(
                    baseStat = 105,
                    effort = 2,
                    stat = Stat(
                        name = "attack",
                        url = ""
                    )
                ),
                PokemonDetailStats(
                    baseStat = 60,
                    effort = 0,
                    stat = Stat(
                        name = "defense",
                        url = ""
                    )
                ),
            ),
            species = PokemonDetailSpecies(
                name = "",
                url = ""
            )
        ),
    )
)
