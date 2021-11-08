package com.example.capstoneproject.model.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class BitsoResponse(
    @Expose @SerializedName("success") val success: Boolean,
    @Expose @SerializedName("payload") val payload: List<Currency>
)

data class Currency(
    @Expose @SerializedName("book") val book: String,
    @Expose @SerializedName("minimum_amount") val minimum_amount: String,
    @Expose @SerializedName("maximum_amount") val maximum_amount: String,
    @Expose @SerializedName("minimum_price") val minimum_price: String,
    @Expose @SerializedName("maximum_price") val maximum_price: String,
    @Expose @SerializedName("minimum_value") val minimum_value: String,
    @Expose @SerializedName("maximum_value") val maximum_value: String,
)
