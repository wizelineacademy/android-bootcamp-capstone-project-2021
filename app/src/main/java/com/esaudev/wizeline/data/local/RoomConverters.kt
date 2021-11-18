package com.esaudev.wizeline.data.local

import androidx.room.TypeConverter
import com.esaudev.wizeline.data.local.entities.AskEntity
import com.esaudev.wizeline.data.local.entities.BidEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class RoomConverters {

    @TypeConverter
    fun fromAskList(list: List<AskEntity>): String {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun toAskList(string: String): List<AskEntity> {
        return Gson().fromJson(string, object : TypeToken<List<AskEntity>>() {}.type)
    }

    @TypeConverter
    fun fromBidList(list: List<BidEntity>): String {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun toBidList(string: String): List<BidEntity> {
        return Gson().fromJson(string, object : TypeToken<List<BidEntity>>() {}.type)
    }

}