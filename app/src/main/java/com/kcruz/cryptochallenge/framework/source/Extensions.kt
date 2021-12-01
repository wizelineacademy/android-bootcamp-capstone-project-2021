package com.kcruz.cryptochallenge.framework.source

import com.kcruz.cryptochallenge.domain.Book
import com.kcruz.cryptochallenge.domain.ExchangeOrderBook
import com.kcruz.cryptochallenge.domain.Order
import com.kcruz.cryptochallenge.framework.database.entity.RBook
import com.kcruz.cryptochallenge.framework.database.entity.RExchangeOrderBook
import com.kcruz.cryptochallenge.framework.database.entity.ROrder

fun ExchangeOrderBook.toEntity(): RExchangeOrderBook = RExchangeOrderBook(
    book, minimumAmount, maximumAmount, minimumPrice, maximumPrice, minimumValue, maximumValue
)

fun RExchangeOrderBook.toModel(): ExchangeOrderBook = ExchangeOrderBook(
    book, minimumAmount, maximumAmount, minimumPrice, maximumPrice, minimumValue, maximumValue
)

fun Book.toEntity(): RBook = RBook(
    book, volume, high, last, low, vwap, ask, bid, createdAt
)

fun RBook.toModel(): Book = Book(
    book, volume, high, last, low, vwap, ask, bid, createdAt
)

fun Order.toEntity(): ROrder = ROrder(
    book, price, amount, "", oid ?: ""
)

fun ROrder.toModel(): Order = Order(
    book, price, amount, oid
)