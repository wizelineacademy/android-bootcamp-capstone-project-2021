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
        maximum_amount = this.maximumAmount,
        maximum_price = this.maximumPrice,
        maximum_value = this.maximumValue,
        minimum_amount = this.minimumAmount,
        minimum_price = this.minimumPrice,
        minimum_value = this.minimumValue,
        icon = iconBook
    )
}

fun List<AvailableBooksPayload>.mapToDomain(): List<AvailableBook> {
    return this.map { it.mapToDomain() }
}