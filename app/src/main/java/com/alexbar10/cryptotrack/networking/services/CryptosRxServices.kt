package com.alexbar10.cryptotrack.networking.services

import com.alexbar10.cryptotrack.domain.OrderResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface CryptosRxServices {
    @GET("order_book/")
    fun getOrderFor(@Query("book") currency: String): Observable<OrderResponse>
}
