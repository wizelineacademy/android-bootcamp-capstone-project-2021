package com.kcruz.cryptochallenge.framework.client

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiClient {

    private var API_SERVICE: ApiService? = null

    fun getService(): ApiService? {
        if (API_SERVICE == null) {
            val httpClient = OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)

            val gson = GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create()

            val retrofit = Retrofit.Builder()
                .baseUrl(SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient.build())
                .build()

            API_SERVICE = retrofit.create(ApiService::class.java)
        }

        return API_SERVICE
    }

    companion object {
        private const val SERVER_URL = "https://api.bitso.com/v3/"
    }
}