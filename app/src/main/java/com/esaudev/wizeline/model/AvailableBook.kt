package com.esaudev.wizeline.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AvailableBook (
    val book: String,
    @SerializedName("maximum_amount") val maximumAmount: String,
    @SerializedName("maximum_price") val maximumPrice: String,
    @SerializedName("maximum_value") val maximumValue: String,
    @SerializedName("minimum_amount") val minimumAmount: String,
    @SerializedName("minimum_price") val minimumPrice: String,
    @SerializedName("minimum_value") val minimumValue: String,
    val icon: String
): Parcelable

