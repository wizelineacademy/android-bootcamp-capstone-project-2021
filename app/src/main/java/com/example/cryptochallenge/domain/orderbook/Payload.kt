package com.example.cryptochallenge.domain.orderbook

/**
 * DTO for a record of order book
 *
 * @property asks List of open asks
 * @property bids List of open bids
 * @property updated_at Timestamp at which the order was last updated
 * @property sequence Increasing integer value for each order book update.
 */
class Payload(
    val asks: List<PayloadObject>? = listOf(),
    val bids: List<PayloadObject>? = listOf(),
    val updated_at: String? = "",
    val sequence: Int? = 0
)