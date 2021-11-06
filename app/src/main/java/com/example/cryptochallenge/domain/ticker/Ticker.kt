package com.example.cryptochallenge.domain.ticker

/**
 * DTO for ticker information
 *
 * @property success Indicator that confirm if response is successful
 * @property payload Ticker information
 */
data class Ticker(
    val success: Boolean? = false,
    val payload: Payload? = null
)