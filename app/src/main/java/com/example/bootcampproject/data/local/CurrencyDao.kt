package com.example.bootcampproject.data.local

import androidx.room.*
import com.example.bootcampproject.domain.Currency

@Dao
interface CurrencyDao {
    @Query("Select * From Currency")
    suspend fun getAll():List<Currency>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(currencies: List<Currency>)

    @Update
    suspend fun updateAll(currencies: List<Currency>)

}