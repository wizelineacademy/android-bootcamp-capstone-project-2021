package com.esaudev.wizeline.data.remote.responses

import com.esaudev.wizeline.model.Ticker

data class TickerResponse(
    val payload: TickerPayload,
    val success: Boolean
)

data class TickerPayload(
    val ask: String,
    val bid: String,
    val book: String,
    val created_at: String,
    val high: String,
    val last: String,
    val low: String,
    val volume: String,
    val vwap: String
)

fun TickerPayload.mapToDomain(): Ticker {
    return Ticker(ask, bid, book, created_at, high, last, low, volume, vwap)
}