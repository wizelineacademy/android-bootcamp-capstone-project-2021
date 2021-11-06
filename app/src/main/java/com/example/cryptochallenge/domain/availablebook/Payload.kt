package com.example.cryptochallenge.domain.availablebook

/**
 * DTO for available book objects
 *
 * @property book Order book symbol
 * @property minimum_amount Minimum amount of major when placing orders
 * @property maximum_amount Maximum amount of major when placing orders
 * @property minimum_price Minimum price when placing orders
 * @property maximum_price Maximum price when placing orders
 * @property minimum_value Minimum value amount (amount*price) when placing orders
 * @property maximum_value Maximum value amount (amount*price) when placing orders
 */
data class Payload(
    val book: String? = "",
    val minimum_amount: Float? = 0F,
    val maximum_amount: Float? = 0F,
    val minimum_price: Float? = 0F,
    val maximum_price: Float? = 0F,
    val minimum_value: Float? = 0F,
    val maximum_value: Float? = 0F
)