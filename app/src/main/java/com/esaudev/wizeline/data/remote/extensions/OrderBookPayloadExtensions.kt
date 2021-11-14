package com.esaudev.wizeline.data.remote.extensions

import com.esaudev.wizeline.data.remote.responses.OrderBookPayload
import com.esaudev.wizeline.model.OrderBook

fun OrderBookPayload.mapToDomain(): OrderBook {
    return OrderBook(
        asks = asks.mapToAskList(),
        bids = bids.mapToBidList(),
        sequence = sequence,
        updated_at = updated_at
    )
}