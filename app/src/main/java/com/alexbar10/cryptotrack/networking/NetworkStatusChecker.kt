package com.alexbar10.cryptotrack.networking

import android.net.ConnectivityManager
import android.net.NetworkCapabilities

class NetworkStatusChecker(private val connectivityManager: ConnectivityManager?) {

    inline fun performIfConnectedToInternet(
        actionNotNetworkAvailable: () -> Unit,
        actionNetworkAvailable: () -> Unit
    ) {
        if (hasInternetConnection()) {
            actionNetworkAvailable()
        } else {
            actionNotNetworkAvailable()
        }
    }

    fun hasInternetConnection(): Boolean {
        val network = connectivityManager?.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false

        return capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_VPN)
    }
}
