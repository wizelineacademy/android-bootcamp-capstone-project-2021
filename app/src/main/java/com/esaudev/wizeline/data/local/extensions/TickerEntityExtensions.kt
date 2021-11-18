package com.esaudev.wizeline.data.local.extensions

import com.esaudev.wizeline.data.local.entities.TickerEntity
import com.esaudev.wizeline.model.Ticker

fun TickerEntity.mapToDomain() = Ticker(
    ask = ask,
    bid = bid,
    book = book,
    created_at = created_at,
    high = high,
    last = last,
    low = low,
    volume = volume,
    vwap = vwap
)