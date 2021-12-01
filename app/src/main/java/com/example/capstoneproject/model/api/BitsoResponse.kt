package com.example.capstoneproject.model.api

import com.google.gson.annotations.SerializedName

data class BitsoResponse(
    @SerializedName(value = "success") val success: Boolean,
    @SerializedName(value = "payload") val payload: List<Currency>
)

data class Currency(
    @SerializedName(value = "book") val book: String,
    @SerializedName(value = "minimum_amount") val minimumAmount: String,
    @SerializedName(value = "maximum_amount") val maximumAmount: String,
    @SerializedName(value = "minimum_price") val minimumPrice: String,
    @SerializedName(value = "maximum_price") val maximumPrice: String,
    @SerializedName(value = "minimum_value") val minimumValue: String,
    @SerializedName(value = "maximum_value") val maximumValue: String,
)

data class BitsoTickerResponse(
    @SerializedName(value = "success") val success: Boolean,
    @SerializedName(value = "payload") val payload: Ticker
)

data class Ticker(
    @SerializedName(value = "book") val book: String,
    @SerializedName(value = "high") val highPrice: String,
    @SerializedName(value = "low") val lowPrice: String,
    @SerializedName(value = "last") val lastPrice: String,
)
