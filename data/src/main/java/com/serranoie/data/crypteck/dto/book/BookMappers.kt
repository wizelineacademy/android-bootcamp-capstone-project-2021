package com.serranoie.data.crypteck.dto.book

import com.serranoie.data.crypteck.utils.Constants

fun AvailableBooksPayload.addImage(): AvailableBookImagePayload {
    val bookArray = this.book.split("_")
    val bookName = "${bookArray.first().uppercase()} - ${bookArray.last().uppercase()}"
    val iconBook = Constants.ICON_BASE_URL + bookArray.first()

    return AvailableBookImagePayload(
        photoUrl = iconBook,
        book = bookName,
        maximum_amount = this.maximum_amount,
        maximum_price = this.maximum_price,
        maximum_value = this.maximum_value,
        minimum_amount = this.minimum_amount,
        minimum_price = this.minimum_price,
        minimum_value = this.minimum_value,
    )
}
