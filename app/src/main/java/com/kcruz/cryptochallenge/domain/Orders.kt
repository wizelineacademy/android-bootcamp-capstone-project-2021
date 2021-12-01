package com.kcruz.cryptochallenge.domain

data class Orders(
    val asks: List<Order>,
    val bids: List<Order>
) {
    companion object {
        const val ASKS = "ASKS"
        const val BIDS = "BIDS"
    }
}