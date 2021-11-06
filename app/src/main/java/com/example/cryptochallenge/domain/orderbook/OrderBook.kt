package com.example.cryptochallenge.domain.orderbook

/**
 * DTO for order books
 *
 * @property success Indicator that confirm if response is successful
 * @property payload Order books info
 */
data class OrderBook(
    val success: Boolean? = false,
    val payload: Payload? = null
)