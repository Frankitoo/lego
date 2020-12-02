package com.frankito.lego.di

import com.frankito.domain.services.BackButtonService
import com.frankito.domain.services.ConnectionService
import com.frankito.domain.services.ToastService
import com.frankito.lego.service.BackButtonServiceImpl
import com.frankito.lego.service.ConnectionServiceImplImpl
import com.frankito.lego.service.ToastServiceImpl
import org.koin.dsl.module

val commonModule = module {
    single<ToastService> { ToastServiceImpl() }
    single<BackButtonService> { BackButtonServiceImpl() }
    single<ConnectionService> { ConnectionServiceImplImpl(get()) }
}
