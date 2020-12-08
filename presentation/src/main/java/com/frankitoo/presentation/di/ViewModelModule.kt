package com.frankitoo.presentation.di

import com.frankitoo.presentation.features.characterdetails.CharacterDetailsViewModel
import com.frankitoo.presentation.features.characterlist.CharacterListViewModel
import com.frankitoo.presentation.features.home.HomeViewModel
import com.frankitoo.presentation.features.main.MainViewModel
import com.frankitoo.presentation.features.weapondetails.WeaponDetailsViewModel
import com.frankitoo.presentation.features.weaponlist.WeaponListViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { MainViewModel(get(), get()) }
    viewModel { CharacterListViewModel(get()) }
    viewModel { CharacterDetailsViewModel(get()) }
    viewModel { WeaponListViewModel(get()) }
    viewModel { WeaponDetailsViewModel(get()) }

    viewModel { HomeViewModel() }
}
