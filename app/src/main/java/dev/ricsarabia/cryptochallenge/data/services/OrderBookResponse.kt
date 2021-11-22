package dev.ricsarabia.cryptochallenge.data.services

data class OrderBookResponse(
    val success: Boolean,
    val payload: Payload
) {
    data class Payload(
        val asks: List<Ask>,
        val bids: List<Bid>,
        val updated_at: String,
        val sequence: String
    ) {
        data class Ask(
            val book: String,
            val price: String,
            val amount: String
        )

        data class Bid(
            val book: String,
            val price: String,
            val amount: String
        )
    }
}