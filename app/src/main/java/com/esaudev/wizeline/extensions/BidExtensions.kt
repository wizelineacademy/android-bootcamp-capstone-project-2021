package com.esaudev.wizeline.extensions

import com.esaudev.wizeline.data.local.entities.BidEntity
import com.esaudev.wizeline.model.Bid

fun Bid.mapToEntity(): BidEntity {
    return BidEntity(
        amount = amount,
        book = book,
        price = price,
        id = id
    )
}

fun List<Bid>.mapToEntity(): List<BidEntity> {
    return this.map { it.mapToEntity() }
}
