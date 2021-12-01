package com.kcruz.cryptochallenge.presentation.currencydetail.order

enum class OrderType(val page: Int, val label: String) {
    ASKS(0, "Asks"),
    BIDS(1, "Bids")
}