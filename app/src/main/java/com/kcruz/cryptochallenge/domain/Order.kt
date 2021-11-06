package com.kcruz.cryptochallenge.domain

data class Order(
    val book: String,
    val price: String,
    val amount: String,
    val oid: String
)