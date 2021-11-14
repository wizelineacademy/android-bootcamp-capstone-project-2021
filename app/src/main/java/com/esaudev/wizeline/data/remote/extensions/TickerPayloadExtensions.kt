package com.esaudev.wizeline.data.remote.extensions

import com.esaudev.wizeline.data.remote.responses.TickerPayload
import com.esaudev.wizeline.model.Ticker

fun TickerPayload.mapToDomain(): Ticker {
    return Ticker(ask, bid, book, created_at, high, last, low, volume, vwap)
}