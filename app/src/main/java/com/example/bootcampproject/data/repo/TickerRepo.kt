package com.example.bootcampproject.data.repo

import com.example.bootcampproject.data.mock.Ticker
import com.example.bootcampproject.data.services.BitsoServices
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TickerRepo @Inject constructor(
    private val bitsoServices : BitsoServices
) {
   suspend fun getTicker(code:String?):Ticker?{
       val call =bitsoServices.getTicker(code)
       try{
           if(call.isSuccessful){
               return call.body()?.payload
           }
           return null
       }catch (e:Exception){
           return null
       }
    }
}