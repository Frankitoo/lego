package com.frankito.presentation

import io.mockk.mockk
import kotlinx.coroutines.flow.FlowCollector

fun <T> mockCollector() = mockk<FlowCollector<T>>(relaxed = true)