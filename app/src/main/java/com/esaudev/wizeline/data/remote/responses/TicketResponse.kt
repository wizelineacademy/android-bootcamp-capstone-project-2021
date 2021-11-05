package com.esaudev.wizeline.data.remote.responses

import com.google.gson.annotations.SerializedName

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