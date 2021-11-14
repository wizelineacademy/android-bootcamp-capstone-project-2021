package com.esaudev.wizeline.data.remote.responses

data class OrderBookPayload(
    val asks: List<AskResponse>,
    val bids: List<BidResponse>,
    val sequence: String,
    val updated_at: String
)
