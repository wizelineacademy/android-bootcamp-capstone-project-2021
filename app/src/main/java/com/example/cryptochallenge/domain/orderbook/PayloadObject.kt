package com.example.cryptochallenge.domain.orderbook

/**
 * DTO for Asks and Bids of OrderBooks
 *
 * @property book Order book symbol
 * @property price Price per unit of major
 * @property amount Major amount in order
 */
class PayloadObject(
    val book: String? = "",
    val price: Double? = 0.0,
    val amount: Double? = 0.0
)