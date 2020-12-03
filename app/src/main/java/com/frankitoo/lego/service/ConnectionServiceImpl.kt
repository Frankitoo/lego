package com.frankitoo.lego.service

import android.content.Context
import android.net.ConnectivityManager
import androidx.core.content.getSystemService
import com.frankitoo.domain.services.ConnectionService

class ConnectionServiceImplImpl(
    private val applicationContext: Context
) : ConnectionService {
    override fun isConnected(): Boolean = applicationContext.getSystemService<ConnectivityManager>()
        ?.activeNetworkInfo?.isConnected ?: false
}
