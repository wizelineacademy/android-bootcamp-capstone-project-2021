package com.serranoie.data.crypteck.di

import com.serranoie.crypteck.domain.usecases.book.GetAvailableBooksUseCase
import com.serranoie.crypteck.domain.usecases.book.GetOpenOrdersBookUseCase
import com.serranoie.crypteck.domain.usecases.trade.GetRecentTradesUseCase
import com.serranoie.crypteck.domain.usecases.trade.GetTickersUseCase
import com.serranoie.data.crypteck.networking.BitsoApi
import com.serranoie.data.crypteck.repository.book.RemoteBookRepositoryImpl
import com.serranoie.data.crypteck.repository.trade.RemoteTradeRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CrypteckDataDIProvider {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(DIProvider.apiHostUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideCrypteckApi(retrofit: Retrofit): BitsoApi {
        return retrofit.create(BitsoApi::class.java)
    }

    @Provides
    @RemoteGetAvailableBooks
    fun provideRemoteGetAvailableBooks(bitsoApi: BitsoApi): GetAvailableBooksUseCase {
        return GetAvailableBooksUseCase(RemoteBookRepositoryImpl(bitsoApi))
    }

    @Provides
    @RemoteGetOpenOrdersBook
    fun providesRemoteGetOpenOrdersBook(bitsoApi: BitsoApi): GetOpenOrdersBookUseCase {
        return GetOpenOrdersBookUseCase(RemoteBookRepositoryImpl(bitsoApi))
    }

    @Provides
    @RemoteGetRecentTrades
    fun providesRemoteGetRecentTrades(bitsoApi: BitsoApi): GetRecentTradesUseCase {
        return GetRecentTradesUseCase(RemoteTradeRepositoryImpl(bitsoApi))
    }

    @Provides
    @RemoteGetTickers
    fun providesRemoteGetTickers(bitsoApi: BitsoApi): GetTickersUseCase {
        return GetTickersUseCase(RemoteTradeRepositoryImpl(bitsoApi))
    }
}