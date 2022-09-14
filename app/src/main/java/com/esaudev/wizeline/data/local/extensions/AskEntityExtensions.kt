package com.esaudev.wizeline.data.local.extensions

import com.esaudev.wizeline.data.local.entities.AskEntity
import com.esaudev.wizeline.model.Ask

fun AskEntity.mapToDomain(): Ask {
    return Ask(
        amount = amount,
        book = book,
        price = price,
        id = id
    )
}

fun List<AskEntity>.mapToDomain(): List<Ask> {
    return this.map { it.mapToDomain() }
}
