package com.frankito.poke

import com.frankito.domain.models.toast.ToastData
import com.frankito.domain.models.toast.ToastDuration
import com.frankito.domain.services.ToastService
import com.frankito.poke.service.ToastServiceImpl
import io.mockk.coVerifyOrder
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class ToastServiceImplTest {
    @get:Rule
    val rule = AppCoroutineRule()

    @Test
    fun `Check toast service behavior`() {
        //given
        val service: ToastService = ToastServiceImpl()

        val collector = mockk<FlowCollector<ToastData>>(relaxed = true)

        val shortMessageData = ToastData(
            duration = ToastDuration.SHORT,
            message = "TestShortMessage"
        )

        val longMessageData = ToastData(
            duration = ToastDuration.LONG,
            message = "TestLongMessage"
        )

        //when
        rule.runBlockingTest {
            val job = launch { service.toastMessage.collect { collector.emit(it) } }

            advanceUntilIdle()
            service.showToast(shortMessageData)
            advanceUntilIdle()
            service.showToast(longMessageData)
            advanceUntilIdle()

            job.cancel()
        }

        //then
        coVerifyOrder {
            collector.emit(match {
                it.duration == ToastDuration.SHORT
                        && it.message == "TestShortMessage"
            })
            collector.emit(match {
                it.duration == ToastDuration.LONG
                        && it.message == "TestLongMessage"
            })
        }
    }
}