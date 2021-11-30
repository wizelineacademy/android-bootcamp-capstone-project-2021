package com.alexbar10.cryptotrack.di

import com.alexbar10.cryptotrack.networking.services.CryptosRxServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

private const val API_BASE_URL = "https://api.bitso.com/v3/"

@Module
@InstallIn(SingletonComponent::class)
object NetworkingRxModule {

    @Provides
    @Singleton
    @Named("provideRxRetrofit")
    fun provideRetrofitClient(): Retrofit {

        return Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideRemoteApiServices(@Named("provideRxRetrofit") retrofit: Retrofit): CryptosRxServices {
        return retrofit.create(CryptosRxServices::class.java)
    }
}
