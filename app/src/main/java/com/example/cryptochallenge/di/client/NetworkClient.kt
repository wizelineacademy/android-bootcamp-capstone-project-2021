package com.example.cryptochallenge.di.client

import com.example.cryptochallenge.data.services.CryptoDetailServices
import com.squareup.moshi.Moshi
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Handle the network client
 */
class NetworkClient {
    companion object {
        /**
         * Base url for perform web service calls
         */
        private const val BASE_URL = "https://api.bitso.com/v3/"
    }

    /**
     * Create and return a handler for web service calls
     *
     * @return Instance of [CryptoDetailServices]
     */
    fun getCryptoDetailService(): CryptoDetailServices {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()

        return retrofit.create(CryptoDetailServices::class.java)
    }
}