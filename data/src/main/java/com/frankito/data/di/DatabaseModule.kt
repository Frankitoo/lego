package com.frankito.data.di

import androidx.room.Room
import com.frankito.data.database.PokemonDatabase
import org.koin.dsl.module

val databaseModule = module {
    single {
        return@single Room.databaseBuilder(get(), PokemonDatabase::class.java, "poke-database")
            .fallbackToDestructiveMigration()
            .build()
    }

    single { get<PokemonDatabase>().pokemonDao() }
}