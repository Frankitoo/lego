package com.frankito.poke.error

import android.content.Context
import com.frankito.domain.error.ErrorHandler
import com.frankito.domain.error.exceptions.NetworkException
import com.frankito.domain.error.exceptions.ServerException
import com.frankito.domain.error.exceptions.UnknownException
import com.frankito.domain.models.toast.ToastData
import com.frankito.domain.services.ConnectionService
import com.frankito.domain.services.ToastService
import com.frankito.poke.R
import kotlinx.coroutines.CoroutineExceptionHandler
import timber.log.Timber
import kotlin.coroutines.CoroutineContext

class ApplicationErrorHandler(
    private val applicationContext: Context,
    private val toastService: ToastService,
    private val connectionService: ConnectionService,
) : ErrorHandler {
    override val key: CoroutineContext.Key<*> = CoroutineExceptionHandler

    override fun handleException(context: CoroutineContext, exception: Throwable) {
        when (exception) {
            is ServerException ->
                toastService.showToast(blockedException("Code: ${exception.errorCode}\nMessage: ${exception.message}"))
            is NetworkException ->
                if (connectionService.isConnected()) {
                    toastService.showToast(connectionException("Message: ${exception.message}"))
                } else {
                    toastService.showToast(
                        connectionException(
                            applicationContext.getString(R.string.common_network_unavailable)
                        )
                    )
                }
            is UnknownException ->
                toastService.showToast(unhappyException("UnknownException message: ${exception.message}"))
        }
        Timber.w(exception)
    }

    companion object {
        fun blockedException(message: String?) = ToastData.ofContent(message = message)

        fun connectionException(message: String?) = ToastData.ofContent(message = message)

        fun unhappyException(message: String?) = ToastData.ofContent(message = message)
    }
}