package com.alexbar10.cryptotrack.domain

data class Order (
    val updated_at: String,
    val bids: List<OrderDetailItem>,
    val asks: List<OrderDetailItem>
)