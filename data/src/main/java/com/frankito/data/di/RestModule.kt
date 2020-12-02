package com.frankito.data.di

import com.frankito.data.api.PokemonApi
import com.frankito.data.api.utils.BaseUrl
import com.frankito.data.api.utils.ErrorParsingCallAdapterFactory
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

import java.util.concurrent.TimeUnit

val restModule = module {
    single { createOkHttpClient() }
    single { createWebService<PokemonApi>(get(), get<BaseUrl>().value) }
}

fun createOkHttpClient(): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
    return OkHttpClient.Builder()
        .connectTimeout(30L, TimeUnit.SECONDS)
        .readTimeout(30L, TimeUnit.SECONDS)
        .addInterceptor(httpLoggingInterceptor)
        .build()
}

inline fun <reified T> createWebService(okHttpClient: OkHttpClient, url: String): T {
    val retrofit = Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create(Moshi.Builder().build()))
        .apply { callAdapterFactories().add(0, ErrorParsingCallAdapterFactory.create()) }
        .build()
    return retrofit.create(T::class.java)
}