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
        maximumAmount = this.maximumAmount,
        maximumPrice = this.maximumPrice,
        maximumValue = this.maximumValue,
        minimumAmount = this.minimumAmount,
        minimumPrice = this.minimumPrice,
        minimumValue = this.minimumValue,
        icon = iconBook
    )
}

fun List<AvailableBooksPayload>.mapToDomain(): List<AvailableBook> {
    return this.map { it.mapToDomain() }
}
