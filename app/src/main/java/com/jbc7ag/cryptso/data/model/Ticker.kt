package com.jbc7ag.cryptso.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class Ticker(
    @SerializedName("success") val success: Boolean,
    @SerializedName("error") val error: Error,
    @SerializedName("payload") val payload: BookDetail
)

@Entity(tableName = "ticker")
data class BookDetail(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @SerializedName("book") val book: String,
    @SerializedName("last") val last: String,
    @SerializedName("high") val high: String,
    @SerializedName("low") val low: String,
    @SerializedName("created_at") val date: String,
    @SerializedName("volume") val volume: String,
    @SerializedName("ask") val ask: String,
    @SerializedName("bid") val bid: String,
    @SerializedName("vwap") val vwap: String,
    @SerializedName("change_24") val change: String
)
