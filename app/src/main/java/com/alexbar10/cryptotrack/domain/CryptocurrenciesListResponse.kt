package com.alexbar10.cryptotrack.domain

data class CryptocurrenciesListResponse (
    val success: Boolean,
    val payload: List<Cryptocurrency>?,
    val error: ErrorResponse?
)