package com.example.cryptochallenge.di

import android.content.Context
import androidx.room.Room
import com.example.cryptochallenge.common.BITSO_API_URL
import com.example.cryptochallenge.common.HEADER_USER_AGENT
import com.example.cryptochallenge.common.HEADER_USER_AGENT_DEFAULT
import com.example.cryptochallenge.common.ROOM_DATABASE_APP
import com.example.cryptochallenge.data.local.AppDatabase
import com.example.cryptochallenge.data.local.AskDAO
import com.example.cryptochallenge.data.local.BidDAO
import com.example.cryptochallenge.data.local.BookDAO
import com.example.cryptochallenge.data.service.ApiService
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    @CoroutinesRetrofit
    fun provideRetrofitInstance(): Retrofit {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)

        val httpClient: OkHttpClient.Builder = OkHttpClient.Builder()
        httpClient.addInterceptor(Interceptor {
            chain ->
            val builder = chain.request().newBuilder()
            builder.header(HEADER_USER_AGENT, System.getProperty("http.agent") ?: HEADER_USER_AGENT_DEFAULT)
            return@Interceptor chain.proceed(builder.build())
        })
        httpClient.addInterceptor(logging)

        return Retrofit.Builder()
            .baseUrl(BITSO_API_URL)
            .addConverterFactory(
                GsonConverterFactory.create(GsonBuilder().create())
            )
            .client(httpClient.build())
            .build()
    }

    @Singleton
    @Provides
    @RxRetrofit
    fun provideRetrofitInstanceForRx(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BITSO_API_URL)
            .addConverterFactory(
                GsonConverterFactory.create(GsonBuilder().create())
            )
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    @ApiServiceWithCoroutines
    fun provideApiService(
        @CoroutinesRetrofit retrofit: Retrofit
    ): ApiService = retrofit.create(ApiService::class.java)

    @Singleton
    @Provides
    @ApiServiceWithRx
    fun provideApiServiceWithRx(
        @RxRetrofit retrofit: Retrofit
    ): ApiService = retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    @IOThread
    fun provideScheduler(): Scheduler =  Schedulers.io()

    @Provides
    @Singleton
    @MainThread
    fun provideShcedulerMainThread(): Scheduler = AndroidSchedulers.mainThread()

    @Provides
    fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()

    @Singleton
    @Provides
    fun provideDispatcherIO(): CoroutineDispatcher = Dispatchers.IO

    @Singleton
    @Provides
    fun provideBookDAO(appDatabase: AppDatabase): BookDAO {
        return appDatabase.bookDao()
    }

    @Singleton
    @Provides
    fun provideAskDAO(appDatabase: AppDatabase): AskDAO {
        return appDatabase.askDao()
    }

    @Singleton
    @Provides
    fun provideBidDAO(appDatabase: AppDatabase): BidDAO {
        return appDatabase.bidDao()
    }

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext applicationContext: Context): AppDatabase {
        return Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            ROOM_DATABASE_APP
        ).build()
    }
}

@Qualifier
annotation class RxRetrofit
@Qualifier
annotation class CoroutinesRetrofit

@Qualifier
annotation class ApiServiceWithRx
@Qualifier
annotation class ApiServiceWithCoroutines

@Qualifier
annotation class MainThread
@Qualifier
annotation class IOThread
