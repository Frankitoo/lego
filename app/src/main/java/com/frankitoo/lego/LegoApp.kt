package com.frankitoo.lego

import android.app.Application
import com.frankitoo.data.di.repositoriesModule
import com.frankitoo.lego.di.commonModule
import com.frankitoo.presentation.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class LegoApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@LegoApp)

            modules(
                commonModule,
                repositoriesModule,
                viewModelModule,
            )
        }
    }
}
