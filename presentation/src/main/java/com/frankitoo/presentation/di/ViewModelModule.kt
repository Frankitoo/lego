package com.frankitoo.presentation.di

import com.frankitoo.presentation.features.characterdetails.CharacterDetailsViewModel
import com.frankitoo.presentation.features.characterlist.CharacterListViewModel
import com.frankitoo.presentation.features.home.HomeViewModel
import com.frankitoo.presentation.features.main.MainViewModel
import com.frankitoo.presentation.features.questdetails.QuestDetailsViewModel
import com.frankitoo.presentation.features.questlist.QuestListViewModel
import com.frankitoo.presentation.features.vehicledetails.VehicleDetailsViewModel
import com.frankitoo.presentation.features.vehiclelist.VehicleListViewModel
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
    viewModel { VehicleListViewModel(get()) }
    viewModel { VehicleDetailsViewModel(get()) }
    viewModel { QuestListViewModel(get()) }
    viewModel { QuestDetailsViewModel(get()) }

    viewModel { HomeViewModel() }
}
