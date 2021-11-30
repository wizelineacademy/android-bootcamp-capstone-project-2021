package com.example.bootcampproject.util

import androidx.room.TypeConverter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

class Converter() {
    /* private val membersType = Types.newParameterizedType(List::class.java, T::class.java)
     private val membersAdapter = moshi.adapter<List<T>>(membersType)

     *//*fun fromDatatoList (data:List<T>){
        val classGeneric: Class<T>

    }*/
    @TypeConverter
    inline fun <reified T : Any> stringToData(string: String): T? {
        val membersType = Types.newParameterizedType(List::class.java, T::class.java)
        val membersAdapter = Moshi.Builder().build().adapter<T>(membersType)
        return membersAdapter.fromJson(string)
    }

    @TypeConverter
    inline fun <reified T : Any> dataToString(data: T): String? {
        val membersType = Types.newParameterizedType(List::class.java, T::class.java)
        val membersAdapter = Moshi.Builder().build().adapter<T>(membersType)
        return membersAdapter.toJson(data)
    }
}
