package com.frankito.data.di

import com.frankito.data.repositories.PokemonRepositoryImpl
import com.frankito.domain.repositories.PokemonRepository
import org.koin.dsl.module

val repositoriesModule = module {
    single<PokemonRepository> { PokemonRepositoryImpl(get(), get(), get()) }
}