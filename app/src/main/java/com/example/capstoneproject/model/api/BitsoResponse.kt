package com.example.capstoneproject.model.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class BitsoResponse(
    @SerializedName("success") val success: Boolean,
    @SerializedName("payload") val payload: List<Currency>
)

data class Currency(
    @SerializedName("book") val book: String,
    @SerializedName("minimum_amount") val minimum_amount: String,
    @SerializedName("maximum_amount") val maximum_amount: String,
    @SerializedName("minimum_price") val minimum_price: String,
    @SerializedName("maximum_price") val maximum_price: String,
    @SerializedName("minimum_value") val minimum_value: String,
    @SerializedName("maximum_value") val maximum_value: String,
)
