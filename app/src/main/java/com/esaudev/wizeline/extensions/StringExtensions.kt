package com.esaudev.wizeline.extensions

fun String.mapToQuery(): String {
    val queryArray = this.lowercase().split(" - ")

    return "${queryArray.first()}_${queryArray.last()}"
}
