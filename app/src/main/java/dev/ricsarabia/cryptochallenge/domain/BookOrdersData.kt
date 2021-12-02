package dev.ricsarabia.cryptochallenge.domain

sealed class BookOrdersData {
    class Data(val bookOrders: BookOrders) : BookOrdersData()
    class Error(val message: String) : BookOrdersData()
}
