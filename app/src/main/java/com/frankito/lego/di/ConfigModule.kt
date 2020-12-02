package com.frankito.lego.di

import com.frankito.lego.BuildConfig
import com.frankito.data.api.utils.BaseUrl
import org.koin.dsl.module

val configModule = module {
    single { BaseUrl(BuildConfig.BASE_URL) }
}
