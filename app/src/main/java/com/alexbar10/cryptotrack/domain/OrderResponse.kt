package com.alexbar10.cryptotrack.domain

data class OrderResponse (
    val success: Boolean,
    val payload: Order
)