package com.esaudev.wizeline.data.remote.interceptors

import android.os.Build
import okhttp3.Interceptor
import okhttp3.Response

class BitsoClientInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val requestWithHeader = request.newBuilder()
            .header("Client-device", Build.DEVICE)
            .build()

        return chain.proceed(requestWithHeader)
    }
}