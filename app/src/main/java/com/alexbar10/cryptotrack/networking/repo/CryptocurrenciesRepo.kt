package com.alexbar10.cryptotrack.networking.repo

import com.alexbar10.cryptotrack.domain.*
import com.alexbar10.cryptotrack.networking.services.CryptocurrenciesServices
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CryptocurrenciesRepo @Inject constructor(private val remoteApiServices: CryptocurrenciesServices) {
    suspend fun getAvailableCurrencies(): Result<CryptocurrenciesListResponse> = try {
        val data = remoteApiServices.getAvailableCurrencies()
        Success(data)
    } catch (error: Throwable) {
        Failure(error)
    }

    suspend fun getTicketFor(currency: String): Result<TickerResponse> = try {
        val data = remoteApiServices.getTickerFor(currency)
        Success(data)
    }  catch (error: Throwable) {
        Failure(error)
    }

    suspend fun getOrderFor(currency: String): Result<OrderResponse> = try {
        val data = remoteApiServices.getOrderFor(currency)
        Success(data)
    }  catch (error: Throwable) {
        Failure(error)
    }
}