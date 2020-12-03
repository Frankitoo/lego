package com.frankitoo.lego.di

import com.frankitoo.domain.services.BackButtonService
import com.frankitoo.domain.services.ConnectionService
import com.frankitoo.domain.services.ToastService
import com.frankitoo.lego.service.BackButtonServiceImpl
import com.frankitoo.lego.service.ConnectionServiceImplImpl
import com.frankitoo.lego.service.ToastServiceImpl
import org.koin.dsl.module

val commonModule = module {
    single<ToastService> { ToastServiceImpl() }
    single<BackButtonService> { BackButtonServiceImpl() }
    single<ConnectionService> { ConnectionServiceImplImpl(get()) }
}
