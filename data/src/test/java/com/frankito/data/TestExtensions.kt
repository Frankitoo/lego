package com.frankito.data

import io.mockk.mockk
import kotlinx.coroutines.flow.FlowCollector

fun <T> mockCollector() = mockk<FlowCollector<T>>(relaxed = true)