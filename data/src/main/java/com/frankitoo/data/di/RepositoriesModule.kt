package com.frankitoo.data.di

import com.frankitoo.data.repositories.character.CharacterRepositoryImpl
import com.frankitoo.data.repositories.weapon.WeaponRepositoryImpl
import com.frankitoo.domain.repositories.CharacterRepository
import com.frankitoo.domain.repositories.WeaponRepository
import com.google.firebase.firestore.FirebaseFirestore
import org.koin.dsl.module

val repositoriesModule = module {
    single<CharacterRepository> { CharacterRepositoryImpl(get()) }
    single<WeaponRepository> { WeaponRepositoryImpl(get()) }
    single { FirebaseFirestore.getInstance() }
}
