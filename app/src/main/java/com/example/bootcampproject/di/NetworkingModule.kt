package com.example.bootcampproject.di

import com.example.bootcampproject.data.services.BitsoServices
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

private const val CURRENCY_BASE_URL = "https://api.bitso.com/v3/"

@Module
@InstallIn(SingletonComponent::class)
object NetworkingModule {

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofitClient(moshi: Moshi): Retrofit {
        val moshiConverterFactory = MoshiConverterFactory.create(moshi)

        return Retrofit.Builder()
            .addConverterFactory(moshiConverterFactory)
            .baseUrl(CURRENCY_BASE_URL)
            .build()
    }

    @Provides
    @Singleton
    fun provideActualCurrency(retrofit: Retrofit): BitsoServices {
        return retrofit.create(BitsoServices::class.java)
    }
}