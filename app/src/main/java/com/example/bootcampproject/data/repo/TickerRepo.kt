package com.example.bootcampproject.data.repo

import com.example.bootcampproject.data.local.OrderBookDao
import com.example.bootcampproject.data.local.TickerDao
import com.example.bootcampproject.data.mock.Ticker
import com.example.bootcampproject.data.services.BitsoServices
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TickerRepo @Inject constructor(
    private val bitsoServices: BitsoServices,
    private val provideTicker: TickerDao
) {
    suspend fun getTicker(code: String?, isConected: Boolean): Ticker? {
        if (isConected) {
            return try {
                val call = bitsoServices.getTicker(code)
                val ticker = call.body()?.payload
                ticker?.book = code
                ticker

            } catch (e: Exception) {
                provideTicker.getSelectedTickers(code)
            }
        }
        return provideTicker.getSelectedTickers(code)
    }
}