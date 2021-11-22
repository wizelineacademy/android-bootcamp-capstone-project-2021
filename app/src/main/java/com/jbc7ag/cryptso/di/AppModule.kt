package com.jbc7ag.cryptso.di

import android.content.Context
import com.jbc7ag.cryptso.data.room.CrypsoRoomDatabase
import com.jbc7ag.cryptso.data.services.CurrencyApi
import com.jbc7ag.cryptso.util.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient().newBuilder().apply {
            addInterceptor { chain ->
                val request = chain.request()
                val builder = request
                    .newBuilder()
                    .header("User-Agent", "Crypso ")
                val mutatedRequest = builder.build()
                val response = chain.proceed(mutatedRequest)
                response
            }
            addInterceptor(interceptor)
        }.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit {

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideCurrencyApi(retrofit: Retrofit): CurrencyApi {
        return retrofit.create(CurrencyApi::class.java)
    }

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) =
        CrypsoRoomDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideCharacterDao(db: CrypsoRoomDatabase) = db.coinListDao()

    @Singleton
    @Provides
    fun provideBooksDao(db: CrypsoRoomDatabase) = db.booksDao()

    @Singleton
    @Provides
    fun provideOrdersDao(db: CrypsoRoomDatabase) = db.ordersDao()

    @Singleton
    @Provides
    fun provideTickerDao(db: CrypsoRoomDatabase) = db.tickerDao()
}