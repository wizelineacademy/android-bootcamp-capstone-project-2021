package com.esaudev.wizeline.data.remote.extensions

import com.esaudev.wizeline.data.remote.responses.AvailableBooksPayload
import com.esaudev.wizeline.model.AvailableBook
import com.esaudev.wizeline.utils.Constants

fun AvailableBooksPayload.mapToDomain(): AvailableBook {
    val bookArray = this.book.split("_")
    val bookName = "${bookArray.first().uppercase()} - ${bookArray.last().uppercase()}"
    val iconBook = Constants.BITSO_ICON_BASE_URL + bookArray.first()

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