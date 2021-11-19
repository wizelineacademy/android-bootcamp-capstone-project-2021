package com.jbc7ag.cryptso.data.repository

import androidx.annotation.WorkerThread
import com.jbc7ag.cryptso.data.model.*
import com.jbc7ag.cryptso.data.room.dao.BooksDao
import com.jbc7ag.cryptso.data.room.dao.CoinListDao
import com.jbc7ag.cryptso.data.room.dao.OrderDao
import com.jbc7ag.cryptso.data.room.dao.TickerDao
import com.jbc7ag.cryptso.data.services.CurrencyApi
import com.jbc7ag.cryptso.util.Resource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CurrencyRepository @Inject constructor(
    private val client: CurrencyApi,
    private val coinListDao: CoinListDao,
    private val BooksDao: BooksDao,
    private val OrderDao: OrderDao,
    private val TickerDao: TickerDao
) {

    //coinList repository
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(coin: Coins) {
        coinListDao.insert(coin)
    }

    //Available Books Repository
    suspend fun downloadAvailableBooks(): Resource<List<Book>> {
        return try {
            val response = client.getAvailableBooks()
            val result = response.body()
            if (response.isSuccessful && result != null) {
                deleteBooks()
                Resource.Success(result.payload)

            } else {
                Resource.Error(result?.error?.message)
            }
        } catch (e: Exception) {
            Resource.Error(e.message)
        }
    }

    fun saveBooks(bookList: List<Book>) {
        BooksDao.insert(bookList)
    }

    fun getBooks(): List<Book> {
        return BooksDao.getBooks()
    }

    private fun deleteBooks() {
        return BooksDao.delete()
    }

    //Orders Repository
    suspend fun downloadOrders(book: String): Resource<OrderDetail> {
        return try {
            val response = client.getOrder(book)
            val result = response.body()
            if (response.isSuccessful && result != null) {
                deleteOrder(book)
                Resource.Success(result.payload)

            } else {
                Resource.Error(result?.error?.message)
            }
        } catch (e: Exception) {
            Resource.Error(e.message)
        }
    }

    fun saveOrder(Order: OrderDetail) {
        OrderDao.insert(Order)
    }

    private fun deleteOrder(book: String) {
        return OrderDao.delete(book)
    }

    fun getOrder(book: String): OrderDetail {
        return OrderDao.getOrder(book)
    }

    // Ticker Repository
    suspend fun downloadTicker(book: String): Resource<BookDetail> {
        return try {
            val response = client.getTicker(book)
            val result = response.body()
            if (response.isSuccessful && result != null) {
                deleteTicker(book)
                Resource.Success(result.payload)

            } else {
                Resource.Error(result?.error?.message)
            }
        } catch (e: Exception) {
            Resource.Error(e.message)
        }
    }

    fun saveTicker(ticker: BookDetail) {
        TickerDao.insert(ticker)
    }

    private fun deleteTicker(book: String) {
        return TickerDao.delete(book)
    }

    fun getTicker(book: String): BookDetail {
        return TickerDao.getTicker(book)
    }
}
