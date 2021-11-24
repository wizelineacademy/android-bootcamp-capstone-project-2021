package dev.ricsarabia.cryptochallenge.utils

import java.lang.Exception
import java.text.DecimalFormat
import java.time.LocalDate
import java.time.OffsetTime
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

fun String.asLocalDate(): String = try {
    val bitsoFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX")
    val date = LocalDate.parse(this, bitsoFormatter)
    val localFormatter = DateTimeFormatter.ofPattern("d MMM yyyy, HH:mm:ss")
    date.atTime(OffsetTime.now()).format(localFormatter)
} catch (e: Exception) {
    "never"
}