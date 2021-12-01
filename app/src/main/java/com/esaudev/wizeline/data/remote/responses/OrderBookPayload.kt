package com.esaudev.wizeline.data.remote.responses

import com.google.gson.annotations.SerializedName

data class OrderBookPayload(
    val asks: List<AskResponse>,
    val bids: List<BidResponse>,
    val sequence: String,
    @SerializedName("updated_at") val updatedAt: String
)
