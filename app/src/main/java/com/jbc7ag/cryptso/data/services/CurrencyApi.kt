package com.jbc7ag.cryptso.data.services

import com.jbc7ag.cryptso.data.model.AvailableBooks
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


@JvmSuppressWildcards
interface CurrencyApi {

    @GET("available_books")
    suspend fun getAvailableBooks(
    ): Response<AvailableBooks>

    @GET("ticker")
    suspend fun getTicker(
        @Query("book") book: String
    ): Response<AvailableBooks>

    @GET("order_book")
    suspend fun getOrder(
        @Query("book") book: String
    ): Response<AvailableBooks>
}