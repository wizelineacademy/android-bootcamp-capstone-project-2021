package dev.ricsarabia.cryptochallenge.utils

import dev.ricsarabia.cryptochallenge.data.services.AvailableBooksResponse
import dev.ricsarabia.cryptochallenge.domain.Book
import java.lang.Exception
import java.text.DecimalFormat
import java.time.*
import java.time.format.DateTimeFormatter

/**
 * Created by Ricardo Sarabia on 2021/11/18.
 */
fun String.asDecimalAmount(): String = try {
    DecimalFormat("#,##0.00######").format(this.toDouble())
} catch (e: Exception) {
    "0.00"
}

fun String.asDecimalPrice(): String = try {
    DecimalFormat("$ #,##0.00######").format(this.toDouble())
} catch (e: Exception) {
    "$ 0.00"
}

fun String.asLocalDate(offset: ZoneOffset = OffsetDateTime.now().offset): String = try {
    val bitsoFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX")
    val bitsoDate = OffsetDateTime.parse(this, bitsoFormatter)
    val localFormatter = DateTimeFormatter.ofPattern("d MMM yyyy, HH:mm:ss")
    bitsoDate.toInstant().atOffset(offset).format(localFormatter)
} catch (e: Exception) {
    "never"
}

fun AvailableBooksResponse.Payload.toBook(): Book {
    val cryptoiconUrl = "https://cryptoicons.org/api/icon/"
    return Book(
        this.book,
        this.book.substringBefore("_"),
        this.book.substringAfter("_"),
        cryptoiconUrl + this.book.substringBefore("_") + "/50"
    )
}

fun String?.toMoneyValue(): Int = try {
    val result = this!!.toDouble() * 100
    result.toInt()
} catch (e: Exception) {
    0
}