package com.frankito.poke.service

import com.frankito.domain.models.toast.ToastData
import com.frankito.domain.services.ToastService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.*

class ToastServiceImpl : ToastService {
    @ExperimentalCoroutinesApi
    private var channel: BroadcastChannel<ToastData?> = ConflatedBroadcastChannel(null)

    @ExperimentalCoroutinesApi
    @FlowPreview
    override val toastMessage: Flow<ToastData>
        get() = channel.openSubscription().consumeAsFlow().drop(1).debounce(debounceTimeInMillis)
            .filterNotNull()

    @ExperimentalCoroutinesApi
    override fun showToast(toastData: ToastData) {
        channel.offer(toastData)
    }

    companion object {
        const val debounceTimeInMillis = 1000L
    }
}