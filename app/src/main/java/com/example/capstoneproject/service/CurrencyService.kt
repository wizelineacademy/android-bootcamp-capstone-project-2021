package com.example.capstoneproject.service

import com.example.capstoneproject.model.api.BitsoResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CurrencyService {

    //@GET("available_books/{name}")
    //fun getCurrencyInfo(@Path("name") currencyName : String) : Call<CurrencyDetails>

    @GET("available_books")
    fun getCurrencyList() : Call<BitsoResponse>

}