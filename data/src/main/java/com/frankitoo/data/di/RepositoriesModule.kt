package com.frankitoo.data.di

import com.frankitoo.data.repositories.CharacterRepositoryImpl
import com.frankitoo.data.repositories.QuestRepositoryImpl
import com.frankitoo.data.repositories.VehicleRepositoryImpl
import com.frankitoo.data.repositories.WeaponRepositoryImpl
import com.frankitoo.domain.repositories.CharacterRepository
import com.frankitoo.domain.repositories.QuestRepository
import com.frankitoo.domain.repositories.VehicleRepository
import com.frankitoo.domain.repositories.WeaponRepository
import com.google.firebase.firestore.FirebaseFirestore
import org.koin.dsl.module

val repositoriesModule = module {
    single<CharacterRepository> { CharacterRepositoryImpl(get()) }
    single<WeaponRepository> { WeaponRepositoryImpl(get()) }
    single<VehicleRepository> { VehicleRepositoryImpl(get()) }
    single<QuestRepository> { QuestRepositoryImpl(get()) }
    single { FirebaseFirestore.getInstance() }
}
