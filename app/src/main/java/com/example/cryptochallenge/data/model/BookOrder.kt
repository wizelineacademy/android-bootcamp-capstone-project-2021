package com.example.cryptochallenge.data.model

data class BookOrders (
    var asks: List<Ask> = emptyList(),
    val bids: List<Bid> = emptyList()
)