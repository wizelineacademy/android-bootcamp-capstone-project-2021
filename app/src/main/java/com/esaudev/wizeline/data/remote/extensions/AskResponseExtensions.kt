package com.esaudev.wizeline.data.remote.extensions

import com.esaudev.wizeline.data.remote.responses.AskResponse
import com.esaudev.wizeline.model.Ask

fun AskResponse.mapToDomain(): Ask {
    val bookArray = this.book.split("_")
    val bookName = "${bookArray.first().uppercase()} - ${bookArray.last().uppercase()}"

    return Ask(
        book = bookName,
        amount = amount,
        price = price
    )
}

fun List<AskResponse>.mapToAskList(): List<Ask> {
    return this.map { it.mapToDomain() }
}
