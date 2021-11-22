package dev.ricsarabia.cryptochallenge.data.repos

import dev.ricsarabia.cryptochallenge.data.db.AppDatabase
import dev.ricsarabia.cryptochallenge.di.BitsoNetworkingModule
import dev.ricsarabia.cryptochallenge.domain.*
import java.lang.Exception

/**
 * Created by Ricardo Sarabia on 2021/11/04.
 * Class to retrieve Bitso data from different sources.
 */
class BitsoRepo(database: AppDatabase) {
    private val remoteDataSource = BitsoNetworkingModule.service
    private val localDataSource = database

    val books = localDataSource.bookDao().getAll()
    fun bookPricesOf(book: String) = localDataSource.bookPricesDao().findById(book)
    fun asksOf(book: String) = localDataSource.bookOrderDao().getAllOf(book, BookOrder.Type.ASK)
    fun bidsOf(book: String) = localDataSource.bookOrderDao().getAllOf(book, BookOrder.Type.BID)

    suspend fun updateBooks(): Boolean {
        val response = try { remoteDataSource.getAvailableBooks() } catch (e: Exception) { null }
        if (response == null || !response.success) return false
        val books: List<Book> = response.payload.map {
            Book(
                it.book,
                it.book.substringBefore("_"),
                it.book.substringAfter("_"),
                "https://cryptoicon-api.vercel.app/api/icon/" + it.book.substringBefore("_")
            )
        }
        localDataSource.bookDao().insert(books)
        return true
    }

    suspend fun updateBookPrices(book: String): Boolean {
        val response = try { remoteDataSource.getTicker(book) } catch (e: Exception) { null }
        if (response == null || !response.success) return false
        val prices = response.payload.run { BookPrices(book, last, high, low) }
        localDataSource.bookPricesDao().insert(prices)
        return true
    }

    suspend fun updateBookOrders(book: String): Boolean {
        val response = try { remoteDataSource.getOrderBook(book) } catch (e: Exception) { null }
        if (response == null || !response.success) return false
        val orders = response.payload.run {
            asks.map { BookOrder(it.book, it.price, it.amount, BookOrder.Type.ASK) } +
            bids.map { BookOrder(it.book, it.price, it.amount, BookOrder.Type.BID) }
        }
        localDataSource.bookOrderDao().deleteOldOrdersOf(book)
        localDataSource.bookOrderDao().insert(orders)
        return true
    }
}