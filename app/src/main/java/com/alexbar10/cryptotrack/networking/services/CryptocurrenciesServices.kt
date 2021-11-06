package com.alexbar10.cryptotrack.networking.services

import com.alexbar10.cryptotrack.domain.CryptocurrenciesListResponse
import com.alexbar10.cryptotrack.domain.OrderResponse
import com.alexbar10.cryptotrack.domain.TickerResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CryptocurrenciesServices {
    @GET("available_books/")
    suspend fun getCryptocurrenciesAvailable(): CryptocurrenciesListResponse

    @GET("ticker/")
    suspend fun getTickerFor(@Query("book") cryptocurrency: String): TickerResponse

    @GET("order_book/")
    suspend fun getOrderFor(@Query("book") cryptocurrency: String): OrderResponse
}