package dev.ricsarabia.cryptochallenge.data.services

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Ricardo Sarabia on 2021/11/04.
 * Interface to declare web requests functions.
 */
interface BitsoService {
    @GET("available_books/")
    suspend fun getAvailableBooks(): AvailableBooksResponse

    @GET("ticker/")
    suspend fun getTicker(@Query("book") book: String): TickerResponse

    @GET("order_book/")
    suspend fun getOrderBook(
        @Query("book") book: String,
        @Query("aggregate") aggregate: Boolean = true
    ): OrderBookResponse
}