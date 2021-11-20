package com.kcruz.cryptochallenge.domain

//TODO: Add documentation

data class Order(
    val book: String,
    val price: String,
    val amount: String,
    val oid: String?
)