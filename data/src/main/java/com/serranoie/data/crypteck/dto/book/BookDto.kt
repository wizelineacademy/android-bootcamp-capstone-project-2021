package com.serranoie.data.crypteck.dto.book

data class AvailableBooksResponse(
    val payload: List<AvailableBookImagePayload>,
    val success: Boolean
)

data class AvailableBooksPayload(
    val book: String,
    val maximum_amount: String,
    val maximum_price: String,
    val maximum_value: String,
    val minimum_amount: String,
    val minimum_price: String,
    val minimum_value: String
)

data class AvailableBookImagePayload(
    val photoUrl: String,
    val book: String,
    val maximum_amount: String,
    val maximum_price: String,
    val maximum_value: String,
    val minimum_amount: String,
    val minimum_price: String,
    val minimum_value: String
)

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