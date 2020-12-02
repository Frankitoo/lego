package com.frankito.poke

import android.app.Application
import com.frankito.data.di.databaseModule
import com.frankito.data.di.repositoriesModule
import com.frankito.data.di.restModule
import com.frankito.poke.di.commonModule
import com.frankito.poke.di.configModule
import com.frankito.presentation.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class PokeApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@PokeApp)

            modules(
                configModule,
                commonModule,
                restModule,
                databaseModule,
                repositoriesModule,
                viewModelModule,
            )
        }
    }
}
