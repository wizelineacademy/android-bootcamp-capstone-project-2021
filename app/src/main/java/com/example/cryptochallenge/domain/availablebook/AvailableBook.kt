package com.example.cryptochallenge.domain.availablebook

data class AvailableBook(
    val success: Boolean? = false,
    val payload: List<Payload>? = listOf()
)