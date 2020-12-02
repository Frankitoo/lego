package com.frankito.domain.services

import kotlinx.coroutines.flow.Flow

interface BackButtonService {
    val isVisible: Flow<Boolean>
    fun visible()
    fun invisible()
}