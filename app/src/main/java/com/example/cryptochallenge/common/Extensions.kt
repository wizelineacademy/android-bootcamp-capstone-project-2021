package com.example.cryptochallenge.common

import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.cryptochallenge.data.model.Book
import com.example.cryptochallenge.data.model.Result
import java.text.NumberFormat

internal fun Fragment.makeToast(message: String) {
    Toast.makeText(
        this.requireContext(),
        message,
        Toast.LENGTH_LONG
    ).show()
}

// String extensions
internal fun String.toNumberFormat(): String {
    return NumberFormat.getInstance().format(this.toDouble())
}

internal fun String.toMoneyFormat(): String {
    return "$${NumberFormat.getInstance().format(this.toDouble())}"
}

// BookDetail extensions
fun Book.getCryptoImage(): String {
    val bookCrypto = this.name.split("_")[0]
    return "https://cryptoicon-api.vercel.app/api/icon/${bookCrypto}"
}

internal fun Book.getConversionName(): String {
    val cryptoName = this.name.split("_")
    return if (cryptoName.size == 2) {
        "${cryptoName[0].uppercase()} en ${cryptoName[1].uppercase()}"
    } else {
        "-"
    }
}

// Result extensions
fun Result.toBook(): Book {
    val book = this.book
    val bookCrypto = this.book.split("_")[0]
    return Book(
        null,
        book,
        "https://cryptoicon-api.vercel.app/api/icon/${bookCrypto}"
    )
}
