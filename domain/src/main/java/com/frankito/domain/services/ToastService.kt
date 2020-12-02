package com.frankito.domain.services

import com.frankito.domain.models.toast.ToastData
import kotlinx.coroutines.flow.Flow

interface ToastService {
    val toastMessage: Flow<ToastData>
    fun showToast(toastData: ToastData)
}