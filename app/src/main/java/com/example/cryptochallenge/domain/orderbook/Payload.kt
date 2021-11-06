package com.example.cryptochallenge.domain.orderbook

class Payload(
    val asks: List<PayloadObject>? = listOf(),
    val bids: List<PayloadObject>? = listOf(),
    val updated_at: String? = "",
    val sequence: Int? = 0
)