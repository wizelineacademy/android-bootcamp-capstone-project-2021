package com.example.bootcampproject.data.services

import com.example.bootcampproject.data.mock.StatusAvailableBooks
import com.example.bootcampproject.data.mock.StatusOrderBook
import com.example.bootcampproject.data.mock.StatusTicker
import io.reactivex.rxjava3.core.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface BitsoServicesObservable {
    @Headers("User-Agent: demo")
    @GET(value = "available_books/")
    fun getAvailableBooks(): Observable<StatusAvailableBooks>

    @Headers("User-Agent: demo")
    @GET(value = "ticker/")
    fun getTicker(@Query(value = "book") id: String?): Observable<StatusTicker>

    @Headers("User-Agent: demo")
    @GET(value = "order_book/")
    fun getOrderBook(@Query(value = "book") id: String?): Observable<StatusOrderBook>
}