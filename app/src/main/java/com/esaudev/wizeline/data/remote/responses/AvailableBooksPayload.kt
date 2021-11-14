package com.esaudev.wizeline.data.remote.responses

data class AvailableBooksPayload(
    val book: String,
    val maximum_amount: String,
    val maximum_price: String,
    val maximum_value: String,
    val minimum_amount: String,
    val minimum_price: String,
    val minimum_value: String
)
