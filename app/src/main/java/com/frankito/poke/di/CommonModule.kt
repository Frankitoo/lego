package com.frankito.poke.di

import com.frankito.domain.error.ErrorHandler
import com.frankito.domain.services.BackButtonService
import com.frankito.domain.services.ConnectionService
import com.frankito.domain.services.ToastService
import com.frankito.poke.error.ApplicationErrorHandler
import com.frankito.poke.service.BackButtonServiceImpl
import com.frankito.poke.service.ConnectionServiceImplImpl
import com.frankito.poke.service.ToastServiceImpl
import com.frankito.poke.ui.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val commonModule = module {
    single<ToastService> { ToastServiceImpl() }
    single<BackButtonService> { BackButtonServiceImpl() }
    single<ConnectionService> { ConnectionServiceImplImpl(get()) }
    single<ErrorHandler> { ApplicationErrorHandler(get(), get(), get()) }

    viewModel { MainViewModel(get(), get()) }
}