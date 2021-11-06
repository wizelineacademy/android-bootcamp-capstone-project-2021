package com.example.cryptochallenge.domain.availablebook

/**
 * DTO for available books
 *
 * @property success Indicator that confirm if response is successful
 * @property payload List of books
 */
data class AvailableBook(
    val success: Boolean? = false,
    val payload: List<Payload>? = listOf()
)