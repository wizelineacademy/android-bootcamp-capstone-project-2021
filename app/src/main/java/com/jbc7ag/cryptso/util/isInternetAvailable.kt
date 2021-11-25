package com.jbc7ag.cryptso.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

fun isInternetAvailable(context: Context): Boolean {

    var result = false
    val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        cm.let {
            it.getNetworkCapabilities(cm.activeNetwork)?.apply {
                result = when {
                    hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                    hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                    else -> false
                }
            }
        }
        return result
    } else {
        cm.activeNetworkInfo.also {
            return it != null && it.isConnected
        }
    }
}
