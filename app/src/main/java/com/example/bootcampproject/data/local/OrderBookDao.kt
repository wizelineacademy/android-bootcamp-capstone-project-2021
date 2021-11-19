package com.example.bootcampproject.data.local

import androidx.room.*
import com.example.bootcampproject.data.mock.OrderBook

@Dao
interface OrderBookDao {
    @Query("Select * From OrderBook")
    suspend fun getAll():List<OrderBook>

    @Query("Select * From OrderBook Where book = :book ")
    suspend fun getSelectedBooks(book:String?):OrderBook

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(books: OrderBook?)

    @Update
    suspend fun updateAll(books: OrderBook)
}