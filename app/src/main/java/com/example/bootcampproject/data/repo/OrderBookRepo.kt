package com.example.bootcampproject.data.repo

import androidx.lifecycle.MutableLiveData
import com.example.bootcampproject.data.mock.OrderBook
import com.example.bootcampproject.data.mock.StatusAvailableBooks
import com.example.bootcampproject.data.mock.StatusOrderBook
import com.example.bootcampproject.data.services.BitsoServices
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType


@Singleton
class OrderBookRepo @Inject constructor(
    private val bitsoServices : BitsoServices
) {

    suspend fun getOrderBooks(code:String?): OrderBook? {
        try {
            val call =bitsoServices.getOrderBook(code)
            if(call.isSuccessful){
                return call.body()?.payload
            }
            return null
        }catch (e:Exception){
            return null
        }
    }
}