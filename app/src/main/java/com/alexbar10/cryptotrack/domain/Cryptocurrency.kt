package com.alexbar10.cryptotrack.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Cryptocurrency (
    val book: String,
    var ticker: Ticker?
    ): Parcelable