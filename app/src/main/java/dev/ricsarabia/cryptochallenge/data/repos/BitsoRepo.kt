package dev.ricsarabia.cryptochallenge.data.repos

import dev.ricsarabia.cryptochallenge.di.BitsoNetworkingModule
import dev.ricsarabia.cryptochallenge.domain.AvailableBooks
import dev.ricsarabia.cryptochallenge.domain.Book

class BitsoRepo {
    suspend fun getAvailableBooks(): AvailableBooks {
        val response = BitsoNetworkingModule.provideBitsoService().getAvailableBooks()
        if (response.body() == null || !response.body()!!.success)
            return AvailableBooks.Error("Error al obtener los datos")

        val books: List<Book> = response.body()!!.payload.map {
            Book(
                it.book,
                it.book.substringBefore("_"),
                it.book.substringAfter("_"),
                "https://cryptoicon-api.vercel.app/api/icon/" + it.book.substringBefore("_")
            )
        }

        return AvailableBooks.Books(books)
    }

    suspend fun getTicker(book: String) {
        val response = BitsoNetworkingModule.provideBitsoService().getTicker(book)
    }

    suspend fun getOrderBook(book: String) {
        val response = BitsoNetworkingModule.provideBitsoService().getOrderBook(book)
    }
}