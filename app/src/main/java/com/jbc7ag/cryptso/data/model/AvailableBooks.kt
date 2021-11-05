package com.jbc7ag.cryptso.data.model

import com.google.gson.annotations.SerializedName

data class AvailableBooks(
    @SerializedName("success") val success: Boolean,
    @SerializedName("error") val error: Error,
    @SerializedName("payload") val payload: List<Book>,
    )

data class Book(
    var name: String,
    @SerializedName("book") val book: String,
    @SerializedName("minimum_amount") val minAmount: String,
    @SerializedName("maximum_amount") val maxAmount: String,
    @SerializedName("minimum_price") val minPrice: String,
    @SerializedName("maximum_price") val maxPrice: String,
    @SerializedName("minimum_value") val minValue: String,
    @SerializedName("maximum_value") val maxValue: String,
)
