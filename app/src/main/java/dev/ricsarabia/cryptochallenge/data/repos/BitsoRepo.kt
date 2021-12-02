package dev.ricsarabia.cryptochallenge.data.repos

import dev.ricsarabia.cryptochallenge.data.db.AppDatabase
import dev.ricsarabia.cryptochallenge.data.services.BitsoService
import dev.ricsarabia.cryptochallenge.domain.*
import dev.ricsarabia.cryptochallenge.utils.toBook
import java.lang.Exception
import javax.inject.Inject

/**
 * Created by Ricardo Sarabia on 2021/11/04.
 * Class to retrieve Bitso data from different sources.
 */
class BitsoRepo @Inject constructor(database: AppDatabase, bitsoService: BitsoService) {
    private val remoteDataSource = bitsoService
    private val localDataSource = database

    fun books() = localDataSource.bookDao().getAll()
    fun bookPricesOf(book: String) = localDataSource.bookPricesDao().findById(book)
    fun asksOf(book: String) = localDataSource.bookOrderDao().getAllOf(book, BookOrder.Type.ASK)
    fun bidsOf(book: String) = localDataSource.bookOrderDao().getAllOf(book, BookOrder.Type.BID)
    fun refreshTimeOf(book: String) = localDataSource.refreshTimeDao().findById(book)

    suspend fun updateBooks(): Boolean {
        val response = try { remoteDataSource.getAvailableBooks() } catch (e: Exception) { null }
        if (response == null || !response.success) return false
        val books = response.payload.map { it.toBook() }
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
        localDataSource.refreshTimeDao().insert(RefreshTime(book, response.payload.updated_at))
        return true
    }
}
