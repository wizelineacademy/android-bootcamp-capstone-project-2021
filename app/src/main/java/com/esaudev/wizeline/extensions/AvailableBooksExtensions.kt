package com.esaudev.wizeline.extensions

import com.esaudev.wizeline.data.local.entities.AvailableBookEntity
import com.esaudev.wizeline.model.AvailableBook

fun AvailableBook.mapToEntity(): AvailableBookEntity{
    return AvailableBookEntity(
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

fun List<AvailableBook>.mapToEntity(): List<AvailableBookEntity>{
    return this.map { it.mapToEntity() }
}