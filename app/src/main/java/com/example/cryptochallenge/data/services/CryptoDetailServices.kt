package com.example.cryptochallenge.data.services

import com.example.cryptochallenge.domain.availablebook.AvailableBook
import com.example.cryptochallenge.domain.orderbook.OrderBook
import com.example.cryptochallenge.domain.ticker.Ticker
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.QueryMap

/**
 * Defines the services to be used
 */
interface CryptoDetailServices {

    /**
     * Get all the available books
     *
     * @return [Observable] for perform a call
     */
    @GET("available_books/")
    fun getAvailableBooks(): Observable<AvailableBook>

    /**
     * Get trading information of a specific book
     *
     * @param params Contains book name
     * @return [Observable] for perform a call
     */
    @GET("ticker/")
    fun getTicker(@QueryMap(encoded = true) params: Map<String, String>): Observable<Ticker>

    /**
     * Get all open books orders of a specific book
     *
     * @param params Contains book name
     * @return [Observable] for perform a call
     */
    @GET("order_book/")
    fun getOrderBook(@QueryMap(encoded = true) params: Map<String, String>): Observable<OrderBook>
}