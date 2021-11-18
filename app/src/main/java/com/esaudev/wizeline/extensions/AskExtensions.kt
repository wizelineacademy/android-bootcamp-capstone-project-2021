package com.esaudev.wizeline.extensions

import com.esaudev.wizeline.data.local.entities.AskEntity
import com.esaudev.wizeline.model.Ask

fun Ask.mapToEntity(): AskEntity {
    return AskEntity(
        amount = amount,
        book = book,
        price = price,
        id = id
    )
}

fun List<Ask>.mapToEntity(): List<AskEntity> {
    return this.map { it.mapToEntity() }
}