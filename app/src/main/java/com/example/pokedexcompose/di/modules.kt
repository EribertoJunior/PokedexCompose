package com.example.pokedexcompose.di

import com.example.pokedexcompose.data.local.RoomConfig
import com.example.pokedexcompose.data.network.PokemonPagingSource
import com.example.pokedexcompose.data.network.RetrofitConfig
import com.example.pokedexcompose.data.dataSource.local.LocalDataSource
import com.example.pokedexcompose.data.dataSource.local.LocalDataSourceImpl
import com.example.pokedexcompose.data.dataSource.remote.RemoteDataSource
import com.example.pokedexcompose.data.dataSource.remote.RemoteDataSourceImpl
import com.example.pokedexcompose.data.repository.DetailRepository
import com.example.pokedexcompose.data.repository.DetailRepositoryImpl
import com.example.pokedexcompose.data.repository.HomeRepository
import com.example.pokedexcompose.data.repository.HomeRepositoryImpl
import com.example.pokedexcompose.data.repository.PokemonRemoteMediator
import com.example.pokedexcompose.ui.viewmodels.DetailsViewModel
import com.example.pokedexcompose.ui.viewmodels.HomeViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

const val PROPERTY_BASE_URL = "SERVER_URL"

val modules = module {
    single {
        val baseUrl = getProperty<String>(PROPERTY_BASE_URL)
        RetrofitConfig(baseUrl, androidContext()).getPokeServide()
    }
    single { RoomConfig.getDataBase(androidContext()) }

    factory { PokemonPagingSource(get()) }

    factory<HomeRepository> {
        HomeRepositoryImpl(
            pokemonRemoteMediator = get(),
            localDataSource = get()
        )
    }
    factory<DetailRepository> {
        DetailRepositoryImpl(
            localDataSource = get()
        )
    }

    factory { get<RoomConfig>().pokemonDao() }
    factory { get<RoomConfig>().pokemonRemoteKeyDao() }
    factory { get<RoomConfig>().pokemonDetailDao() }
    factory { get<RoomConfig>().pokemonSpeciesDao() }
    factory { get<RoomConfig>().pokemonEvolutionChainDao() }

    factory<RemoteDataSource> { RemoteDataSourceImpl(get()) }
    factory<LocalDataSource> {
        LocalDataSourceImpl(
            pokemonDao = get(),
            pokemonDetailDao = get(),
            pokemonSpeciesDao = get(),
            evolutionChainDao = get(),
            pokemonRemoteKeyDao = get()
        )
    }

    factory {
        PokemonRemoteMediator(
            remoteDataSource = get(),
            localDataSource = get()
        )
    }

    viewModel { HomeViewModel(repository = get()) }
    viewModel { DetailsViewModel(detailRepository = get()) }
}