package com.example.bootcampproject.util

import androidx.room.TypeConverter
import com.example.bootcampproject.data.mock.Bids
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

class ConverterBids() {
    private val membersType = Types.newParameterizedType(List::class.java, Bids::class.java)
    private val membersAdapter = Moshi.Builder().build().adapter<List<Bids>>(membersType)

    @TypeConverter
    fun stringToData(string: String): List<Bids>? {
        return membersAdapter.fromJson(string)
    }

    @TypeConverter
    fun dataToString(data: List<Bids>): String? {
        return membersAdapter.toJson(data)
    }
}