package com.frankito.poke.di

import com.frankito.poke.BuildConfig
import com.frankito.data.api.utils.BaseUrl
import org.koin.dsl.module

val configModule = module {
    single { BaseUrl(BuildConfig.BASE_URL) }
}