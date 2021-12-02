package dev.ricsarabia.cryptochallenge.data.services

data class AvailableBooksResponse(
    val success: Boolean,
    val payload: List<Payload>
) {
    data class Payload(
        val book: String,
        val minimum_amount: String,
        val maximum_amount: String,
        val minimum_price: String,
        val maximum_price: String,
        val minimum_value: String,
        val maximum_value: String
    )
}
