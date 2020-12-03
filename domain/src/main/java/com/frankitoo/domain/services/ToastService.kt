package com.frankitoo.domain.services

import com.frankitoo.domain.models.toast.ToastData
import kotlinx.coroutines.flow.Flow

interface ToastService {
    val toastMessage: Flow<ToastData>
    fun showToast(toastData: ToastData)
}
