package com.frankito.presentation.di

import com.frankito.presentation.features.pokemondetails.PokemonDetailsViewModel
import com.frankito.presentation.features.pokemonlist.PokemonListViewModel
import com.frankito.presentation.features.pokemonpager.PokemonPagerViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { PokemonListViewModel(get()) }
    viewModel { PokemonDetailsViewModel(get(), get()) }
    viewModel { PokemonPagerViewModel(get(), get(), get(), get()) }
}