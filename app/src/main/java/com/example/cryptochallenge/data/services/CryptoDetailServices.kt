package com.example.cryptochallenge.data.services

import com.example.cryptochallenge.domain.availablebook.AvailableBook
import com.example.cryptochallenge.domain.orderbook.OrderBook
import com.example.cryptochallenge.domain.ticker.Ticker
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface CryptoDetailServices {

    @GET("available_books/")
    fun getAvailableBooks(): Observable<AvailableBook>

    @GET("ticker/")
    fun getTicker(@QueryMap(encoded = true) params: Map<String, String>): Observable<Ticker>

    @GET("order_book/")
    fun getOrderBook(@QueryMap(encoded = true) params: Map<String, String>): Observable<OrderBook>
}