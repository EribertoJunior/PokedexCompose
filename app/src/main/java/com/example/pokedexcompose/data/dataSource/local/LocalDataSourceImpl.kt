package com.example.pokedexcompose.data.dataSource.local

import androidx.paging.PagingSource
import com.example.pokedexcompose.data.local.dao.EvolutionChainDao
import com.example.pokedexcompose.data.local.dao.PokemonDao
import com.example.pokedexcompose.data.local.dao.PokemonDetailDao
import com.example.pokedexcompose.data.local.dao.PokemonRemoteKeyDao
import com.example.pokedexcompose.data.local.dao.PokemonSpeciesDao
import com.example.pokedexcompose.data.local.entities.EvolutionChainEntity
import com.example.pokedexcompose.data.local.entities.PokemonEntity
import com.example.pokedexcompose.data.local.entities.PokemonDetail
import com.example.pokedexcompose.data.local.entities.PokemonRemoteKeyEntity
import com.example.pokedexcompose.data.local.entities.PokemonSpeciesEntity
import com.example.pokedexcompose.data.local.relations.PokemonAndDetail
import kotlinx.coroutines.flow.Flow

class LocalDataSourceImpl(
    private val pokemonDao: PokemonDao,
    private val pokemonRemoteKeyDao: PokemonRemoteKeyDao,
    private val pokemonDetailDao: PokemonDetailDao,
    private val pokemonSpeciesDao: PokemonSpeciesDao,
    private val evolutionChainDao: EvolutionChainDao,
): LocalDataSource {
    override fun getPokemons(): PagingSource<Int, PokemonAndDetail> {
        return pokemonDao.getPokemons()
    }

    override fun searchPokemonByName(name: String): Flow<PokemonAndDetail> {
        return pokemonDao.searchPokemonByName(name)
    }

    override suspend fun deleteAllPokemon() {
        pokemonDao.deleteAll()
    }

    override suspend fun saveAllPokemons(pokemonEntities: List<PokemonEntity>) {
        return pokemonDao.saveAll(pokemonEntities)
    }

    override suspend fun savePokemon(pokemonEntity: PokemonEntity) {
        return pokemonDao.save(pokemonEntity)
    }

    override suspend fun saveAllRemoteKey(pokemonRemoteKeyEntities: List<PokemonRemoteKeyEntity>) {
        return pokemonRemoteKeyDao.saveAll(pokemonRemoteKeyEntities)
    }

    override suspend fun getPokemonRemoteKeyByName(pokemonName: String): PokemonRemoteKeyEntity {
        return pokemonRemoteKeyDao.getPokemonRemoteKeyFromName(pokemonName)
    }

    override suspend fun deleteAllRemoteKey() {
        pokemonRemoteKeyDao.deleteAll()
    }

    override suspend fun saveRemoteKey(pokemonRemoteKeyEntity: PokemonRemoteKeyEntity) {
        pokemonRemoteKeyDao.save(pokemonRemoteKeyEntity)
    }

    override suspend fun saveAllPokemonDetail(pokemonDetails: List<PokemonDetail>) {
        pokemonDetailDao.saveAll(pokemonDetails)
    }

    override suspend fun saveAllPokemonSpecies(species: List<PokemonSpeciesEntity>) {
        pokemonSpeciesDao.saveAllSpecie(species)
    }

    override suspend fun saveAllEvolutionChain(evolutionChainEntity: List<EvolutionChainEntity>) {
        evolutionChainDao.saveAll(evolutionChainEntity)
    }

    override suspend fun saveEvolutionChain(evolutionChainEntity: EvolutionChainEntity) {
        evolutionChainDao.save(evolutionChainEntity)
    }

    override fun searchEvolutionChainById(chainId: Int): EvolutionChainEntity? {
        return evolutionChainDao.searchEvolutionChainById(chainId)
    }

}