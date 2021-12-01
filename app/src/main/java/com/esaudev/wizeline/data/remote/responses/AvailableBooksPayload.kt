package com.esaudev.wizeline.data.remote.responses

import com.google.gson.annotations.SerializedName

data class AvailableBooksPayload(
    val book: String,
    @SerializedName("maximum_amount") val maximumAmount: String,
    @SerializedName("maximum_price") val maximumPrice: String,
    @SerializedName("maximum_value") val maximumValue: String,
    @SerializedName("minimum_amount") val minimumAmount: String,
    @SerializedName("minimum_price") val minimumPrice: String,
    @SerializedName("minimum_value") val minimumValue: String
)
