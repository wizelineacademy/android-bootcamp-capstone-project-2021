package com.jbc7ag.cryptso.util

import android.content.Context
import android.util.Log
import com.jbc7ag.cryptso.R
import com.jbc7ag.cryptso.data.model.*
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

fun String.getCurrencyCodeFilter(): String {
    if (this.isEmpty()) return EMPTY_STRING
    return this.substring(this.indexOf(DELIMITER)+1, this.length)
}

fun String.formatCurrency(): String {
    if (this.isEmpty()) return EMPTY_STRING

    val formatter: NumberFormat = DecimalFormat("$#,###.###")
    return formatter.format(this.toFloat())
}

fun String.formatMaxPrice(code: String, context: Context): String {
    if (code.isEmpty()) return EMPTY_STRING

    val convertCode = code.substring(code.indexOf(DELIMITER) + 1, code.length).uppercase()
    return "${context.getString(R.string.maximum_price)} ${this.formatCurrency()} ${convertCode.uppercase()}"
}


fun List<Book>.getFilterList(item: String?): List<Filter> {
    val filters = mutableListOf<Filter>()
    this.map {
        filters.add(Filter(it.book.getCurrencyCodeFilter(), it.book.getCurrencyCodeFilter() == item))
    }
    return filters.distinctBy{ it.name }
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

