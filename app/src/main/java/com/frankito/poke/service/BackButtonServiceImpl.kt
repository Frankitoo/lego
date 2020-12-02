package com.frankito.poke.service

import com.frankito.domain.services.BackButtonService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.filterNotNull

class BackButtonServiceImpl : BackButtonService {
    @ExperimentalCoroutinesApi
    private var channel: BroadcastChannel<Boolean?> = ConflatedBroadcastChannel(null)

    @FlowPreview
    @ExperimentalCoroutinesApi
    override val isVisible: Flow<Boolean>
        get() = channel.openSubscription().consumeAsFlow().drop(1).filterNotNull()

    @ExperimentalCoroutinesApi
    override fun visible() {
        channel.offer(true)
    }

    @ExperimentalCoroutinesApi
    override fun invisible() {
        channel.offer(false)
    }
}
