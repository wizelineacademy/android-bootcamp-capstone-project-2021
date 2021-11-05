package dev.ricsarabia.cryptochallenge.data.repos

import dev.ricsarabia.cryptochallenge.di.BitsoNetworkingModule
import dev.ricsarabia.cryptochallenge.domain.*

class BitsoRepo {
    suspend fun getBooks(): BooksData {
        val response = BitsoNetworkingModule.provideBitsoService().getAvailableBooks()

        if (response.body() == null || !response.body()!!.success)
            return BooksData.Error("Error al obtener los datos")

        val books: List<Book> = response.body()!!.payload.map {
            Book(
                it.book,
                it.book.substringBefore("_"),
                it.book.substringAfter("_"),
                "https://cryptoicon-api.vercel.app/api/icon/" + it.book.substringBefore("_")
            )
        }

        return BooksData.Data(books)
    }

    suspend fun getBookPrices(book: String): BookPricesData {
        val response = BitsoNetworkingModule.provideBitsoService().getTicker(book)

        if (response.body() == null || !response.body()!!.success)
            return BookPricesData.Error("Error al obtener los datos")

        val payload = response.body()!!.payload
        val prices = BookPrices(
            payload.book,
            payload.last,
            payload.high,
            payload.low
        )

        return BookPricesData.Data(prices)
    }

    suspend fun getBookOrders(book: String): BookOrdersData {
        val response = BitsoNetworkingModule.provideBitsoService().getOrderBook(book)

        if (response.body() == null || !response.body()!!.success)
            return BookOrdersData.Error("Error al obtener los datos")

        val payload = response.body()!!.payload
        val orders = BookOrders(
            payload.asks.map { BookOrder(it.book, it.price, it.amount) },
            payload.bids.map { BookOrder(it.book, it.price, it.amount) }
        )

        return BookOrdersData.Data(orders)
    }
}