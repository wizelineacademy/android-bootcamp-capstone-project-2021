package com.example.bootcampproject.data.mock

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.Index
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class StatusOrderBook(
    @Json(name = "success") val success: Boolean,
    @Json(name = "payload") val payload: OrderBook
)

@Entity(indices = [Index(value = ["book"], unique = true)])
@JsonClass(generateAdapter = true)
data class OrderBook(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    var book: String? = null,
    @Json(name = "updated_at") val updated_at: String,
    @Json(name = "sequence") val sequence: Long,
    @Json(name = "bids") val bids: List<Bids>,
    @Json(name = "asks") val asks: List<Asks>
)


@JsonClass(generateAdapter = true)
data class Asks(

    @Json(name = "book") val book: String,
    @Json(name = "price") val price: Double,
    @Json(name = "amount") val amount: Double
)

@JsonClass(generateAdapter = true)
data class Bids(
    @Json(name = "book") val book: String,
    @Json(name = "price") val price: Double,
    @Json(name = "amount") val amount: Double
)


