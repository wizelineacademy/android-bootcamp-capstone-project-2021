package com.serranoie.crypteck.domain.models

data class AvailableBooks(
    val photoUrl: String?,
    val book: String?,
    val minimumAmount: String?,
    val maximumAmount: String?,
    val minimumPrice: String?,
    val maximumPrice: String?,
    val minimumValue: String?,
    val maximumValue: String?,
)