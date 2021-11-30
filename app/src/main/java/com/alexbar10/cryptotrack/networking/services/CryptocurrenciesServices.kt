package com.alexbar10.cryptotrack.networking.services

import com.alexbar10.cryptotrack.domain.CryptocurrenciesListResponse
import com.alexbar10.cryptotrack.domain.OrderResponse
import com.alexbar10.cryptotrack.domain.TickerResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface CryptocurrenciesServices {
    @Headers("apiKey: 21435465458657")
    @GET("available_books/")
    suspend fun getCryptocurrenciesAvailable(): CryptocurrenciesListResponse

    @Headers("apiKey: 21435465458657")
    @GET("ticker/")
    suspend fun getTickerFor(@Query("book") cryptocurrency: String): TickerResponse

    @Headers("apiKey: 21435465458657")
    @GET("order_book/")
    suspend fun getOrderFor(@Query("book") cryptocurrency: String): OrderResponse
}
