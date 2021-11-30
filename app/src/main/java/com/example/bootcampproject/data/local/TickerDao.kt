package com.example.bootcampproject.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.bootcampproject.data.mock.Ticker

@Dao
interface TickerDao {
    @Query("Select * From ${DBConstantTablesName.ticker}")
    suspend fun getAll(): List<Ticker>

    @Query("Select * From ${DBConstantTablesName.ticker} Where book = :book ")
    suspend fun getSelectedTickers(book: String?): Ticker

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(tickers: Ticker?)

    @Update
    suspend fun updateAll(currencies: Ticker)
}
