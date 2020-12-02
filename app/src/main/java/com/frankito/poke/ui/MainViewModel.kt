package com.frankito.poke.ui

import com.frankito.domain.models.toast.ToastData
import com.frankito.domain.services.BackButtonService
import com.frankito.domain.services.ToastService
import com.frankito.presentation.base.BaseViewModel
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