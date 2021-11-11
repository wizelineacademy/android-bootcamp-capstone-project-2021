package com.jbc7ag.cryptso.util

import com.jbc7ag.cryptso.data.model.AvailableBooks
import com.jbc7ag.cryptso.data.model.Book
import com.jbc7ag.cryptso.data.model.Orders
import com.jbc7ag.cryptso.data.model.Ticker
import retrofit2.Response
import java.text.DecimalFormat
import java.text.NumberFormat


fun String.getmarketFormat(): String {
    if (this.isEmpty()) return EMPTY_STRING

    return this.replace(DELIMITER, "/").uppercase()
}


fun String.getCurrencyCode(): String {
    if (this.isEmpty()) return EMPTY_STRING

    return this.substring(0, this.indexOf(DELIMITER))
}

fun String.formatCurrency(): String {
    if (this.isEmpty()) return EMPTY_STRING

    val formatter: NumberFormat = DecimalFormat("$#,###.###")
    return formatter.format(this.toFloat())
}

fun String.formatPrice(code: String): String {
    if (code.isEmpty()) return EMPTY_STRING

    val convertCode = code.substring(code.indexOf(DELIMITER) + 1, code.length).uppercase()
    return "Max: ${this.formatCurrency()} ${convertCode.uppercase()}"
}


fun List<Book>.setNameCurrencies(): List<Book> {
    this.map { it.name = it.book.substring(0, it.book.indexOf(DELIMITER)) }
    return this
}

fun Response<Orders>?.getOrderError(): String {
    if (this?.body() == null) return EMPTY_STRING
    return "${this.body()?.error?.code}: ${this.body()?.error?.message}"
}

fun Response<Ticker>?.getTickerError(): String {
    if (this?.body() == null) return EMPTY_STRING
    return "${this.body()?.error?.code}: ${this.body()?.error?.message}"
}

fun Response<AvailableBooks>?.getBooksError(): String {
    if (this?.body() == null) return EMPTY_STRING
    return "${this.body()?.error?.code}: ${this.body()?.error?.message}"
}

