package com.esaudev.wizeline.data.local.extensions

import com.esaudev.wizeline.data.local.entities.OrderBookEntity
import com.esaudev.wizeline.model.OrderBook

fun OrderBookEntity.mapToDomain() = OrderBook(
    asks = asks.mapToDomain(),
    bids = bids.mapToDomain(),
    sequence = sequence,
    updated_at = updated_at
)