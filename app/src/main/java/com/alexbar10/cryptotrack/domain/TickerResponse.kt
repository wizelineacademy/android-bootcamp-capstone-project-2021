package com.alexbar10.cryptotrack.domain

data class TickerResponse (
    val success: Boolean,
    val payload: Ticker?,
    val errorResponse: ErrorResponse?
)