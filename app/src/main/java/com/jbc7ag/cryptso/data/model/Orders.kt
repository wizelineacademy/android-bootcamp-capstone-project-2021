package com.jbc7ag.cryptso.data.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class Orders (
    @SerializedName("success") val success: Boolean,
    @SerializedName("error") val error: Error,
    @SerializedName("payload") val payload: OrderDetail,
)

data class OrderDetail(
    @SerializedName("updated_at") val date: Date,
    @SerializedName("sequence") val sequence: Long,
    @SerializedName("bids") val bids: List<Bids>
)

data class Bids(
    @SerializedName("book") val book: String,
    @SerializedName("price") val price: String,
    @SerializedName("amount") val amount: String
)