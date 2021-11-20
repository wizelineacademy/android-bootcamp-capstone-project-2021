package com.kcruz.cryptochallenge.framework.source

import com.kcruz.cryptochallenge.domain.ExchangeOrderBook
import com.kcruz.cryptochallenge.framework.database.entity.RExchangeOrderBook

fun ExchangeOrderBook.toEntity(): RExchangeOrderBook = RExchangeOrderBook(
    book, minimumAmount, maximumAmount, minimumPrice, maximumPrice, minimumValue, maximumValue
)


fun RExchangeOrderBook.toModel(): ExchangeOrderBook = ExchangeOrderBook(
    book, minimumAmount, maximumAmount, minimumPrice, maximumPrice, minimumValue, maximumValue
)