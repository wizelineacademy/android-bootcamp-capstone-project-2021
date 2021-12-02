package com.example.cryptochallenge.data.service

import io.reactivex.Observable
import com.example.cryptochallenge.data.model.GetBooks
import com.example.cryptochallenge.data.model.GetBookDetail
import com.example.cryptochallenge.data.model.GetBookOrdersDetail
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("available_books")
    fun getBooksWithRx(): Observable<GetBooks>

    @GET("available_books")
    suspend fun getBooks(): GetBooks

    @GET("order_book")
    suspend fun getBookOrders(@Query("book") book: String): GetBookOrdersDetail

    @GET("ticker")
    suspend fun getBookDetail(@Query("book") book: String): GetBookDetail
}