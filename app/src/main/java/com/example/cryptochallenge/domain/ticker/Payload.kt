package com.example.cryptochallenge.domain.ticker

/**
 * DTO for Ticker record
 *
 * @property book Order book symbol
 * @property volume Last 24 hours volume
 * @property high Last 24 hours price high
 * @property last Last traded price
 * @property low Last 24 hours price low
 * @property vwap Last 24 hours volume weighted average price: vwap
 * @property ask Lowest sell order
 * @property bid Highest buy order
 * @property created_at Timestamp at which the ticker was generated
 */
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