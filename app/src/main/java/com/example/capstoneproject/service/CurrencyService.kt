package com.example.capstoneproject.service

import com.example.capstoneproject.model.api.BitsoResponse
import com.example.capstoneproject.model.api.BitsoTickerResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CurrencyService {

    @GET("ticker")
    fun getCurrencyInfo(@Query("book") bookName : String) : Call<BitsoTickerResponse>

    @GET("available_books")
    fun getCurrencyList() : Call<BitsoResponse>

}