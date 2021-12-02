package dev.ricsarabia.cryptochallenge.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.ricsarabia.cryptochallenge.BuildConfig
import dev.ricsarabia.cryptochallenge.data.services.BitsoService
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Ricardo Sarabia on 2021/11/04.
 * Object to initialize and configure Retrofit's api to execute web requests.
 */
@Module
@InstallIn(SingletonComponent::class)
object BitsoNetworkingModule {
    private const val BITSO_BASE_URL = BuildConfig.BITSO_URL
    @Provides
    @Singleton
    fun provideNetworkInterceptor() = Interceptor { chain ->
        chain.proceed(
            chain.request().newBuilder()
                .addHeader("User-Agent", System.getProperty("http.agent"))
                .build()
        )
    }

    @Provides
    @Singleton
    fun provideLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        networkInterceptor: Interceptor,
        loggingInterceptor: HttpLoggingInterceptor
    ) = OkHttpClient.Builder()
        .addInterceptor(networkInterceptor)
        .addInterceptor(loggingInterceptor)
        .build()

    @Provides
    @Singleton
    fun provideBitsoService(okHttpClient: OkHttpClient): BitsoService = Retrofit
        .Builder()
        .baseUrl(BITSO_BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build().run { create(BitsoService::class.java) }
}
