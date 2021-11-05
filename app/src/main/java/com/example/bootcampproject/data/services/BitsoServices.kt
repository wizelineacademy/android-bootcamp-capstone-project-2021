package com.example.bootcampproject.data.services

import com.example.bootcampproject.data.mock.AvailableBook
import retrofit2.Call
import retrofit2.http.GET


interface BitsoServices {
    @GET(value = "available_books/")
    fun getAvailableBooks():Call<AvailableBook>

    @GET(value = "ticker/")
    fun getTicker()

    @GET(value = "order_book/")
    fun getOrderBook()
}