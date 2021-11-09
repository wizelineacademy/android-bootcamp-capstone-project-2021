package com.serranoie.crypteck.domain.models

data class Trades(
    val book: String?,
    val createdAt: String?,
    val amount: String?,
    val markerSides: String,
    val price: String?,
    val tid: String?,
)