package com.esaudev.wizeline.model

data class OrderBook(
    val asks: List<Ask>,
    val bids: List<Bid>,
    val sequence: String,
    val updated_at: String
)