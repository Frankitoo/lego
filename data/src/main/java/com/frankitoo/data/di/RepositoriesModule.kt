package com.frankitoo.data.di

import com.frankitoo.data.repositories.CharacterRepositoryImpl
import com.frankitoo.domain.repositories.CharacterRepository
import org.koin.dsl.module

val repositoriesModule = module {
    single<CharacterRepository> { CharacterRepositoryImpl() }
}