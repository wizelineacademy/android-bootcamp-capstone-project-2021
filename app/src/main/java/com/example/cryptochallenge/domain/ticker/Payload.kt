package com.example.cryptochallenge.domain.ticker

class Payload(
    val book: String? = "",
    val volume: Double? = 0.0,
    val high: Double? = 0.0,
    val last: Double? = 0.0,
    val low: Double? = 0.0,
    val vwap: Double? = 0.0,
    val ask: Double? = 0.0,
    val bid: Double? = 0.0,
    val created_at: String? = ""
)