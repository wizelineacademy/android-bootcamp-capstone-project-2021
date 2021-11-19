package com.example.bootcampproject.data.repo

import android.util.Log
import com.example.bootcampproject.data.local.OrderBookDao
import com.example.bootcampproject.data.mock.OrderBook
import com.example.bootcampproject.data.services.BitsoServices
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class OrderBookRepo @Inject constructor(
    private val bitsoServices : BitsoServices,
    private val provideOrderBooks: OrderBookDao
) {

    suspend fun getOrderBooks(code:String?,isConected:Boolean): OrderBook? {

        if(isConected) {
            try {
                val call = bitsoServices.getOrderBook(code)
                val orderBook = call.body()?.payload
                orderBook?.book = code
                provideOrderBooks.insert(orderBook)
                return orderBook
                }
            catch (e: Exception) {
                val r=e
                r.message?.let { Log.d("tag111", it) }
                return provideOrderBooks.getSelectedBooks(code)
            }
        }

        return provideOrderBooks.getSelectedBooks(code)
    }
}