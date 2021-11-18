package com.esaudev.wizeline.model

import java.util.*

data class Bid(
    val amount: String,
    val book: String,
    val price: String,
    val id: String = UUID.randomUUID().toString()
)