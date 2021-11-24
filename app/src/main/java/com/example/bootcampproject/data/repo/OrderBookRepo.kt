package com.example.bootcampproject.data.repo

import android.util.Log
import com.example.bootcampproject.data.local.OrderBookDao
import com.example.bootcampproject.data.mock.OrderBook
import com.example.bootcampproject.data.services.BitsoServices
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton


@Singleton
class OrderBookRepo @Inject constructor(
    private val bitsoServices: BitsoServices,
    private val provideOrderBooks: OrderBookDao
) {

    suspend fun getOrderBooks(code: String?, isConected: Boolean): OrderBook? {

        if (isConected) {
            return try {
                val call = bitsoServices.getOrderBook(code)
                val orderBook = call.body()?.payload
                orderBook?.book = code
                orderBook
            } catch (e: Exception) {
                provideOrderBooks.getSelectedBooks(code)
            }
        }

        return provideOrderBooks.getSelectedBooks(code)
    }
}