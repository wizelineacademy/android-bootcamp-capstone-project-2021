package com.kcruz.cryptochallenge.framework.client

import com.kcruz.cryptochallenge.framework.response.AvailableBooksResponse
import com.kcruz.cryptochallenge.framework.response.BookResponse
import com.kcruz.cryptochallenge.framework.response.OrderBookResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface ApiService {

    @GET("available_books/")
    suspend fun getAvailableBooks(): Response<AvailableBooksResponse>

    @GET("ticker/")
    suspend fun getBookInfo(@QueryMap(encoded = true) params: Map<String, String>): Response<BookResponse>

    @GET("order_book/")
    suspend fun getOpenOrders(@QueryMap(encoded = true) params: Map<String, String>): Response<OrderBookResponse>

}