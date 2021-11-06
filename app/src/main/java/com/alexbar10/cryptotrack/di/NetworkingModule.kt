package com.alexbar10.cryptotrack.di

import com.alexbar10.cryptotrack.networking.services.CryptocurrenciesServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

private const val API_BASE_URL = "https://api.bitso.com/v3/"

@Module
@InstallIn(SingletonComponent::class)
object NetworkingModule {

    @Provides
    @Singleton
    fun provideRetrofitClient(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideRemoteApiServices(retrofit: Retrofit): CryptocurrenciesServices {
        return retrofit.create(CryptocurrenciesServices::class.java)
    }
}