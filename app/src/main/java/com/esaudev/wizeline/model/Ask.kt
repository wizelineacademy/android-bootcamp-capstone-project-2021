package com.esaudev.wizeline.model

import java.util.UUID

data class Ask(
    val amount: String,
    val book: String,
    val price: String,
    val id: String = UUID.randomUUID().toString()
)
