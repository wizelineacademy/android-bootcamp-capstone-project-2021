package dev.ricsarabia.cryptochallenge.domain

sealed class BookPricesData {
    class Data(val bookPrices: BookPrices) : BookPricesData()
    class Error(val message: String) : BookPricesData()
}
