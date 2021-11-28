package com.example.bootcampproject.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.bootcampproject.domain.Currency

@Dao
interface CurrencyDao {
    @Query("Select * From Currency")
    suspend fun getAll(): List<Currency>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(currencies: List<Currency>)

    @Update
    suspend fun updateAll(currencies: List<Currency>)
}
