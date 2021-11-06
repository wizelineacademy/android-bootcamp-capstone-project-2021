package com.esaudev.wizeline.model

import java.util.*

data class OrderBook(
    val asks: List<Ask>,
    val bids: List<Bid>,
    val sequence: String,
    val updated_at: String
)

data class Bid(
    val amount: String,
    val book: String,
    val price: String,
    val id: String = UUID.randomUUID().toString()
)

data class Ask(
    val amount: String,
    val book: String,
    val price: String,
    val id: String = UUID.randomUUID().toString()
)