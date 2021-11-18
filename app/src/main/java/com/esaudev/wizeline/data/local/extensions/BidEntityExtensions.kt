package com.esaudev.wizeline.data.local.extensions

import com.esaudev.wizeline.data.local.entities.AskEntity
import com.esaudev.wizeline.data.local.entities.BidEntity
import com.esaudev.wizeline.model.Ask
import com.esaudev.wizeline.model.Bid

fun BidEntity.mapToDomain(): Bid {
    return Bid(
        amount = amount,
        book = book,
        price = price,
        id = id
    )
}

fun List<BidEntity>.mapToDomain(): List<Bid> {
    return this.map { it.mapToDomain() }
}