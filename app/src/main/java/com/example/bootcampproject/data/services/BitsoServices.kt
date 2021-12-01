package com.example.bootcampproject.data.services

import com.example.bootcampproject.data.mock.StatusAvailableBooks
import com.example.bootcampproject.data.mock.StatusOrderBook
import com.example.bootcampproject.data.mock.StatusTicker
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface BitsoServices {
    @Headers("User-Agent: demo")
    @GET(value = "available_books/")
    suspend fun getAvailableBooks(): Response<StatusAvailableBooks>

    @Headers("User-Agent: demo")
    @GET(value = "ticker/")
    suspend fun getTicker(@Query(value = "book") id: String?): Response<StatusTicker>

    @Headers("User-Agent: demo")
    @GET(value = "order_book/")
    suspend fun getOrderBook(@Query(value = "book") id: String?): Response<StatusOrderBook>
}
