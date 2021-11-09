package com.serranoie.crypteck.domain.models

data class Payload(
    val asks: List<Asks>,
    val bids: List<Bids>,
    val sequence: String,
    val updated_at: String
)