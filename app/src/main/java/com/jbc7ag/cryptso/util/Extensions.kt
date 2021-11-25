package com.jbc7ag.cryptso.util

import com.jbc7ag.cryptso.data.model.Book
import com.jbc7ag.cryptso.data.model.Filter
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
    return this.substring(this.indexOf(DELIMITER) + 1, this.length)
}

fun String.formatCurrency(): String {
    if (this.isEmpty()) return EMPTY_STRING

    val formatter: NumberFormat = DecimalFormat("$#,###.###")
    return formatter.format(this.toFloat())
}

fun List<Book>.getFilterList(item: String?): List<Filter> {
    val filters = mutableListOf<Filter>()
    this.map {
        filters.add(
            Filter(
                it.book.getCurrencyCodeFilter(),
                it.book.getCurrencyCodeFilter() == item
            )
        )
    }
    return filters.distinctBy { it.name }
}
