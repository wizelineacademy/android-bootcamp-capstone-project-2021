package com.esaudev.wizeline.data.remote.responses

import com.esaudev.wizeline.model.Ask
import com.esaudev.wizeline.model.Bid
import com.esaudev.wizeline.model.OrderBook

data class OrderBookResponse(
    val payload: OrderBookPayload,
    val success: Boolean
)


