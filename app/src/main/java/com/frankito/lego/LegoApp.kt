package com.frankito.lego

import android.app.Application
import com.frankito.data.di.databaseModule
import com.frankito.data.di.repositoriesModule
import com.frankito.data.di.restModule
import com.frankito.lego.di.commonModule
import com.frankito.lego.di.configModule
import com.frankito.presentation.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class LegoApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@LegoApp)

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
