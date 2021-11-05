package com.esaudev.wizeline.data.remote.api

import com.esaudev.wizeline.data.remote.responses.AvailableBooksResponse
import com.esaudev.wizeline.data.remote.responses.OrderBookResponse
import com.esaudev.wizeline.data.remote.responses.TicketResponse
import retrofit2.Response
import retrofit2.http.GET

interface BitsoApi {

    @GET("available_books/")
    suspend fun getAvailableBooks(): Response<AvailableBooksResponse>

    @GET("ticker/")
    suspend fun getTicker(): Response<TicketResponse>

    @GET("order_book/")
    suspend fun getOrderBook(): Response<OrderBookResponse>

}