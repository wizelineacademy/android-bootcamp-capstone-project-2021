package dev.ricsarabia.cryptochallenge.data

data class TickerResponse(
  val success: Boolean,
  val payload: Payload
) {
  data class Payload(
    val book: String,
    val volume: String,
    val high: String,
    val last: String,
    val low: String,
    val vwap: String,
    val ask: String,
    val bid: String,
    val created_at: String
  )
}