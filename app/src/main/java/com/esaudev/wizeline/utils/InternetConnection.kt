package com.esaudev.wizeline.utils

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.InetSocketAddress
import java.net.Socket

object InternetConnection {
    suspend fun isNetworkAvailable() = withContext(Dispatchers.IO) {
        try {
            Socket().use { socket ->
                socket.connect(InetSocketAddress("www.google.com", 80), 4000)
                true
            }
        } catch (e: Exception) {
            false
        }
    }
}
