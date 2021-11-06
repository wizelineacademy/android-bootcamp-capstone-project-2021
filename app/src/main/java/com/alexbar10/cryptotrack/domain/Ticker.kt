package com.alexbar10.cryptotrack.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Ticker (
    var high: Double,
    var last: Double,
    var low: Double,
    var book: String
): Parcelable