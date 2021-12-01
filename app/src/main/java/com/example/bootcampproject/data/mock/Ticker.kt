package com.example.bootcampproject.data.mock

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class StatusTicker(
    @Json(name = "success") val success: Boolean,
    @Json(name = "payload") val payload: Ticker,
)

@Entity(indices = [Index(value = ["book"], unique = true)])
@JsonClass(generateAdapter = true)
data class Ticker(

    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    @Json(name = "high") val high: Double,
    @Json(name = "last") val last: Double,
    @Json(name = "created_at") val created_at: String,
    @Json(name = "book") var book: String? = null,
    @Json(name = "volume") val volume: Double,
    @Json(name = "vwap") val vwap: Double,
    @Json(name = "low") val low: Double,
    @Json(name = "ask") val ask: Double,
    @Json(name = "bid") val bid: Double,
    @Json(name = "change_24") val change_24: Double
)
