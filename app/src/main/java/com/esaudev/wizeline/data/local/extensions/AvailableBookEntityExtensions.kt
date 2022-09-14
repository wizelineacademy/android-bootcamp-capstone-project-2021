package com.esaudev.wizeline.data.local.extensions

import com.esaudev.wizeline.data.local.entities.AvailableBookEntity
import com.esaudev.wizeline.model.AvailableBook

fun AvailableBookEntity.mapToDomain(): AvailableBook {
    return AvailableBook(
        book = book,
        maximumAmount = maximumAmount,
        maximumPrice = maximumPrice,
        maximumValue = maximumValue,
        minimumAmount = minimumAmount,
        minimumPrice = minimumPrice,
        minimumValue = minimumValue,
        icon = icon
    )
}

fun List<AvailableBookEntity>.mapToDomain(): List<AvailableBook> {
    return this.map { it.mapToDomain() }
}
