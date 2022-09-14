package com.esaudev.wizeline.data.remote.extensions

import com.esaudev.wizeline.data.remote.responses.BidResponse
import com.esaudev.wizeline.model.Bid

fun BidResponse.mapToDomain(): Bid {
    val bookArray = this.book.split("_")
    val bookName = "${bookArray.first().uppercase()} - ${bookArray.last().uppercase()}"

    return Bid(
        book = bookName,
        amount = amount,
        price = price
    )
}

fun List<BidResponse>.mapToBidList(): List<Bid> {
    return this.map { it.mapToDomain() }
}
