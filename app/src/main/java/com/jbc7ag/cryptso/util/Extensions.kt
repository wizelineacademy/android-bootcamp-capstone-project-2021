package com.jbc7ag.cryptso.util

import com.jbc7ag.cryptso.data.model.Book
import java.text.DecimalFormat
import java.text.NumberFormat

fun String.getmarketFormat(): String {
    if(this.isEmpty()) return ""

    return  this.replace("_", "/").uppercase()
}

//Get only the Currency code
fun String.getCurrencyCode(): String {
    if(this.isEmpty()) return ""

    return  this.substring(0, this.indexOf('_'))
}

fun String.formatCurrency(): String{
    if(this.isEmpty()) return ""

    val formatter: NumberFormat = DecimalFormat("$ #,###.###")
    return formatter.format(this.toFloat())
}

fun String.formatPrice(code: String): String{
    if(code.isEmpty()) return ""

    val convertCode = code.substring(code.indexOf('_')+1, code.length).uppercase()
    return "Max: ${this.formatCurrency()} ${convertCode.uppercase()}"
}

//Set currencies name, to not show the code
fun List<Book>.setNameCurrencies(): List<Book> {
   this.map { it.name = it.book.substring(0, it.book.indexOf('_')) }
  return this
}

// Filter list to not repeat currencies
fun List<Book>.filterCurrencies(): List<Book> {
  return this//.distinctBy { it.name }
}
