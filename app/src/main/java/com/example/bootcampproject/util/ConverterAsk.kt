package com.example.bootcampproject.util

import androidx.room.TypeConverter
import com.example.bootcampproject.data.mock.Asks
import com.example.bootcampproject.data.mock.Bids
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

class ConverterAsk {
    private val membersType = Types.newParameterizedType(List::class.java, Asks::class.java)
    private val membersAdapter= Moshi.Builder().build().adapter<List<Asks>>(membersType)

    @TypeConverter
    fun  stringToData(string:String):List<Asks>?  {
        return membersAdapter.fromJson(string)
    }
    @TypeConverter
    fun  dataToString(data:List<Asks>):String?  {
        return membersAdapter.toJson(data)
    }
}