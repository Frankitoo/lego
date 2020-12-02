package com.frankito.poke.service

import android.content.Context
import android.net.ConnectivityManager
import androidx.core.content.getSystemService
import com.frankito.domain.services.ConnectionService

class ConnectionServiceImplImpl(
    private val applicationContext: Context
) : ConnectionService {
    override fun isConnected(): Boolean = applicationContext.getSystemService<ConnectivityManager>()
        ?.activeNetworkInfo?.isConnected ?: false
}