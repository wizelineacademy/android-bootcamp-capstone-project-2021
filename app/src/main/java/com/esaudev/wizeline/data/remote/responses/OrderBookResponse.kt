package com.esaudev.wizeline.data.remote.responses

data class OrderBookResponse(
    val payload: OrderBookPayload,
    val success: Boolean
)

data class OrderBookPayload(
    val asks: List<Ask>,
    val bids: List<Bid>,
    val sequence: String,
    val updated_at: String
)

data class Bid(
    val amount: String,
    val book: String,
    val price: String
)

data class Ask(
    val amount: String,
    val book: String,
    val price: String
)