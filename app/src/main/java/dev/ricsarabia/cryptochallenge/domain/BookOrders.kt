package dev.ricsarabia.cryptochallenge.domain

data class BookOrders(
    val asks: List<BookOrder>,
    val bids: List<BookOrder>
)
