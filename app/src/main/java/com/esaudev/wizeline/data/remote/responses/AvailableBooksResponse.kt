package com.esaudev.wizeline.data.remote.responses

import com.esaudev.wizeline.model.AvailableBook

data class AvailableBooksResponse(
    val payload: List<AvailableBooksPayload>,
    val success: Boolean
)

data class AvailableBooksPayload(
    val book: String,
    val maximum_amount: String,
    val maximum_price: String,
    val maximum_value: String,
    val minimum_amount: String,
    val minimum_price: String,
    val minimum_value: String
)

fun AvailableBooksPayload.mapToDomain(): AvailableBook {
    val bookArray = this.book.split("_")
    val bookName = "${bookArray.first().uppercase()} - ${bookArray.last().uppercase()}"
    val iconBook = ICON_BASE_URL + bookArray.first()

    return AvailableBook(
        book = bookName,
        maximum_amount = this.maximum_amount,
        maximum_price = this.maximum_price,
        maximum_value = this.maximum_value,
        minimum_amount = this.minimum_amount,
        minimum_price = this.minimum_price,
        minimum_value = this.minimum_value,
        icon = iconBook
    )
}

fun List<AvailableBooksPayload>.mapToDomain(): List<AvailableBook> {
    return this.map { it.mapToDomain() }
}

const val ICON_BASE_URL = "https://cryptoicon-api.vercel.app/api/icon/"

