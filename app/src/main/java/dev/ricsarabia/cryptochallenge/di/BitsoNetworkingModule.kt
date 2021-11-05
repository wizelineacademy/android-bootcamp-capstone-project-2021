package dev.ricsarabia.cryptochallenge.di

import dev.ricsarabia.cryptochallenge.data.services.BitsoService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by Ricardo Sarabia on 2021/11/04.
 * Object to initialize and configure Retrofit's api to execute web requests.
 */

object BitsoNetworkingModule {
    private const val BITSO_BASE_URL = "https://api.bitso.com/v3/"

    private fun provideRetrofitClient(): Retrofit {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .build()

        return Retrofit.Builder()
            .baseUrl(BITSO_BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun provideBitsoService(): BitsoService {
        return provideRetrofitClient().create(BitsoService::class.java)
    }
}