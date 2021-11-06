package com.esaudev.wizeline.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AvailableBook (
    val book: String,
    val maximum_amount: String,
    val maximum_price: String,
    val maximum_value: String,
    val minimum_amount: String,
    val minimum_price: String,
    val minimum_value: String,
    val icon: String
): Parcelable

