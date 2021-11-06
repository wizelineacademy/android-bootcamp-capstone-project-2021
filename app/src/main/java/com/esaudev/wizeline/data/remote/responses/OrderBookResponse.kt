package com.esaudev.wizeline.data.remote.responses

import com.esaudev.wizeline.model.Ask
import com.esaudev.wizeline.model.Bid
import com.esaudev.wizeline.model.OrderBook

data class OrderBookResponse(
    val payload: OrderBookPayload,
    val success: Boolean
)

data class OrderBookPayload(
    val asks: List<AskResponse>,
    val bids: List<BidResponse>,
    val sequence: String,
    val updated_at: String
)

data class BidResponse(
    val amount: String,
    val book: String,
    val price: String
)

data class AskResponse(
    val amount: String,
    val book: String,
    val price: String
)

fun OrderBookPayload.mapToDomain(): OrderBook {
    return OrderBook(
        asks = asks.mapToAskList(),
        bids = bids.mapToBidList(),
        sequence = sequence,
        updated_at = updated_at
    )
}

fun BidResponse.mapToDomain(): Bid {
    val bookArray = this.book.split("_")
    val bookName = "${bookArray.first().uppercase()} - ${bookArray.last().uppercase()}"

    return Bid(
        book = bookName,
        amount = amount,
        price = price
    )
}

fun AskResponse.mapToDomain(): Ask {
    val bookArray = this.book.split("_")
    val bookName = "${bookArray.first().uppercase()} - ${bookArray.last().uppercase()}"

    return Ask(
        book = bookName,
        amount = amount,
        price = price
    )
}

fun List<BidResponse>.mapToBidList(): List<Bid> {
    return this.map { it.mapToDomain() }
}

fun List<AskResponse>.mapToAskList(): List<Ask> {
    return this.map { it.mapToDomain() }
}