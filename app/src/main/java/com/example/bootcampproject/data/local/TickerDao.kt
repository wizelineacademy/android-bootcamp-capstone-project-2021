package com.example.bootcampproject.data.local

import androidx.room.*
import com.example.bootcampproject.data.mock.Ticker

@Dao
interface TickerDao {
    @Query("Select * From Ticker")
    suspend fun getAll(): List<Ticker>

    @Query("Select * From Ticker Where book = :book ")
    suspend fun getSelectedTickers(book: String?): Ticker

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(tickers: Ticker?)

    @Update
    suspend fun updateAll(currencies: Ticker)
}