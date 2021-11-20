package dev.ricsarabia.cryptochallenge.utils

import java.lang.Exception
import java.text.DecimalFormat

/**
 * Created by Ricardo Sarabia on 2021/11/18.
 */
fun String.asDecimalAmount(): String = try {
    DecimalFormat("#,##0.00").format(this.toDouble())
} catch (e: Exception) {
    "0.00"
}

fun String.asDecimalPrice(): String = try {
    DecimalFormat("$ #,##0.00").format(this.toDouble())
} catch (e: Exception) {
    "$ 0.00"
}
