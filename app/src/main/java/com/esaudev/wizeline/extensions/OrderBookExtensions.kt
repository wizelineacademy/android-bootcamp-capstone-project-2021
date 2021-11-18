package com.esaudev.wizeline.extensions

import com.esaudev.wizeline.data.local.entities.OrderBookEntity
import com.esaudev.wizeline.model.OrderBook

fun OrderBook.mapToEntity(book: String) = OrderBookEntity(
    asks = asks.mapToEntity(),
    bids = bids.mapToEntity(),
    sequence = sequence,
    updated_at = updated_at,
    book = book
)