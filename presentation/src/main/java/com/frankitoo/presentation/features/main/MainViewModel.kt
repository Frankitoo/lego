package com.frankitoo.presentation.features.main

import com.frankitoo.domain.models.toast.ToastData
import com.frankitoo.domain.services.BackButtonService
import com.frankitoo.domain.services.ToastService
import com.frankitoo.presentation.base.BaseViewModel
import kotlinx.coroutines.flow.*

class MainViewModel(
    private val toastService: ToastService,
    private val backButtonService: BackButtonService,
) : BaseViewModel() {
    val toast by liveData<ToastData> {
        toastService.toastMessage.collect { emit(it) }
    }

    fun mainViewState(): Flow<MainViewState> =
        backButtonService.isVisible.map { MainViewState(it) }
}
