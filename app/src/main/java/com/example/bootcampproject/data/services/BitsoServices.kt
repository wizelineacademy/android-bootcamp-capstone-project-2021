package com.example.bootcampproject.data.services

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface BitsoServices {
    @GET(value = "available_books/")
    fun getAvailableBooks()

    @GET(value = "ticker/")
    fun getTicker()

    @GET(value = "order_book/")
    fun getOrderBook()
}