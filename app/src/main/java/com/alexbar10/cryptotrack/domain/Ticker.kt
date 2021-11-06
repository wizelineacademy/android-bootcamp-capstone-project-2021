package com.alexbar10.cryptotrack.domain

data class Ticker (
    var high: Double,
    var last: Double,
    var low: Double,
    var book: String
)