package com.example.bootcampproject.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.bootcampproject.data.mock.OrderBook

@Dao
interface OrderBookDao {
    @Query("Select * From ${DBConstantTablesName.orderBook}")
    suspend fun getAll(): List<OrderBook>

    @Query("Select * From ${DBConstantTablesName.orderBook} Where book = :book ")
    suspend fun getSelectedBooks(book: String?): OrderBook

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(books: OrderBook?)

    @Update
    suspend fun updateAll(books: OrderBook)
}
