package com.example.bootcampproject.di

import com.example.bootcampproject.data.services.BitsoServices
import com.example.bootcampproject.data.services.BitsoServicesObservable
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named
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


    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor() = HttpLoggingInterceptor()
        .apply {
            level = HttpLoggingInterceptor.Level.HEADERS
        }

    @Provides
    @Singleton
    fun provideOkhttp(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {

        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    @Named("Normal")
    fun provideRetrofitClient(moshi: Moshi, okHttpClient: OkHttpClient): Retrofit {
        val moshiConverterFactory = MoshiConverterFactory.create(moshi)
        return Retrofit.Builder()
            .addConverterFactory(moshiConverterFactory)
            .baseUrl(CURRENCY_BASE_URL)
            .client(okHttpClient)
            .build()
    }
    @Provides
    @Singleton
    @Named("Observable")
    fun provideRetrofitClientObservable(moshi: Moshi, okHttpClient: OkHttpClient): Retrofit {
        val moshiConverterFactory = MoshiConverterFactory.create(moshi)
        return Retrofit.Builder()
            .addConverterFactory(moshiConverterFactory)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .baseUrl(CURRENCY_BASE_URL)
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun  provideActualCurrency (@Named("Normal") retrofit: Retrofit): BitsoServices {
        return retrofit.create(BitsoServices::class.java)
    }

    @Provides
    @Singleton
    fun  provideActualCurrencyObservable (@Named("Observable") retrofit: Retrofit): BitsoServicesObservable {
        return retrofit.create(BitsoServicesObservable::class.java)
    }

}