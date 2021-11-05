package dev.ricsarabia.cryptochallenge.data.repos

import dev.ricsarabia.cryptochallenge.di.BitsoNetworkingModule

class BitsoRepo {
    suspend fun getAvailableBooks() {
        val response = BitsoNetworkingModule.provideBitsoService().getAvailableBooks()
    }

    suspend fun getTicker(book: String) {
        val response = BitsoNetworkingModule.provideBitsoService().getTicker(book)
    }

    suspend fun getOrderBook(book: String) {
        val response = BitsoNetworkingModule.provideBitsoService().getOrderBook(book)
    }
}