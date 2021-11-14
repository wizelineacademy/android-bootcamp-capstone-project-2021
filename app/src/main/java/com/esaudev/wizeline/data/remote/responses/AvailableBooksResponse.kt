package com.esaudev.wizeline.data.remote.responses

data class AvailableBooksResponse(
    val payload: List<AvailableBooksPayload>,
    val success: Boolean
)

