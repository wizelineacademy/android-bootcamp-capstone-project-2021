package com.jbc7ag.cryptso.data.model

import com.google.gson.annotations.SerializedName

data class Ticker (
        @SerializedName("success") val success: Boolean,
        @SerializedName("error") val error: Error,
        @SerializedName("payload") val payload: BookDetail,
    )

    data class BookDetail(
        @SerializedName("book") val book: String,
        @SerializedName("last") val last: String,
        @SerializedName("high") val high: String,
        @SerializedName("low") val low: String,
        @SerializedName("created_at") val date: String,
        @SerializedName("volume") val volume: String,
        @SerializedName("ask") val ask: String,
        @SerializedName("bid") val bid: String,
        @SerializedName("vwap") val vwap: String,
        @SerializedName("change_24") val change: String,
    )