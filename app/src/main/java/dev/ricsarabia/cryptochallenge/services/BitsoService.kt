package dev.ricsarabia.cryptochallenge.services

import dev.ricsarabia.cryptochallenge.data.AvailableBooksResponse
import dev.ricsarabia.cryptochallenge.data.OrderBookResponse
import dev.ricsarabia.cryptochallenge.data.TickerResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface BitsoService {
    @GET("available_books/")
    suspend fun getAvailableBooks(): Response<AvailableBooksResponse>

    @GET("ticker/?book={book}")
    suspend fun getTicker(@Path("book") id: String): Response<TickerResponse>

    @GET("order_book/?book={book}")
    suspend fun getOrderBook(@Path("book") id: String): Response<OrderBookResponse>
}