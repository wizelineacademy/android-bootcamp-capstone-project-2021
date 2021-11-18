package com.esaudev.wizeline.data.local

import androidx.room.*
import com.esaudev.wizeline.data.local.entities.AvailableBookEntity
import com.esaudev.wizeline.data.local.entities.OrderBookEntity
import com.esaudev.wizeline.data.local.entities.TickerEntity
import com.esaudev.wizeline.model.AvailableBook
import kotlinx.coroutines.flow.Flow

@Dao
interface BitsoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBook(book: AvailableBookEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllBooks(books: List<AvailableBookEntity>)

    @Query("DELETE FROM books")
    suspend fun deleteAllBooks()

    @Query("SELECT * FROM books")
    fun getAllBooks(): List<AvailableBookEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrderBook(book: OrderBookEntity)

    @Query("SELECT * FROM order_books WHERE book = :book")
    fun getOrderBookByBook(book: String): OrderBookEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBookTicker(ticker: TickerEntity)

    @Query("SELECT * FROM tickers WHERE book = :book")
    fun getTickerFromBook(book: String): TickerEntity
}