package com.serranoie.data.crypteck.dto.trade

data class TicketResponse(
    val payload: TicketPayload,
    val success: Boolean
)

data class TicketPayload(
    val ask: String,
    val bid: String,
    val book: String,
    val created_at: String,
    val high: String,
    val last: String,
    val low: String,
    val volume: String,
    val vwap: String
)