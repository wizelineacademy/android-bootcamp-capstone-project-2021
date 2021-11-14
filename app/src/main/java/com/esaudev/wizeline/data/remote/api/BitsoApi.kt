package com.esaudev.wizeline.data.remote.api

import com.esaudev.wizeline.data.remote.responses.AvailableBooksResponse
import com.esaudev.wizeline.data.remote.responses.OrderBookResponse
import com.esaudev.wizeline.data.remote.responses.TickerResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface BitsoApi {

    @GET("available_books")
    suspend fun getAvailableBooks(): Response<AvailableBooksResponse>

    @GET("ticker")
    suspend fun getTicker(
        @Query("book") book: String
    ): Response<TickerResponse>

    @GET("order_book")
    suspend fun getOrderBook(
        @Query("book") book: String
    ): Response<OrderBookResponse>

}