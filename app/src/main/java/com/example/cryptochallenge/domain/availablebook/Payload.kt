package com.example.cryptochallenge.domain.availablebook

data class Payload(
    val book: String? = "",
    val minimum_amount: Float? = 0F,
    val maximum_amount: Float? = 0F,
    val minimum_price: Float? = 0F,
    val maximum_price: Float? = 0F,
    val minimum_value: Float? = 0F,
    val maximum_value: Float? = 0F
)