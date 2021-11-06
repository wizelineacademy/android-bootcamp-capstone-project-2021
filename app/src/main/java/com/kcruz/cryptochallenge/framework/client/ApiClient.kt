package com.kcruz.cryptochallenge.framework.client

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {

    private var API_SERVICE: ApiService? = null

    fun getService(): ApiService? {
        if (API_SERVICE == null) {
            val gson = GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create()

            val retrofit = Retrofit.Builder()
                .baseUrl(SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

            API_SERVICE = retrofit.create(ApiService::class.java)
        }

        return API_SERVICE
    }

    companion object {
        private const val SERVER_URL = "https://api.bitso.com/v3/"
    }
}