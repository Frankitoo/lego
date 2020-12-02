package com.frankito.domain.models.toast

data class ToastData(
    val duration: ToastDuration?,
    val message: String?
) {
    companion object {
        fun ofContent(message: String?) = ToastData(
            duration = ToastDuration.SHORT,
            message = message
        )
    }
}