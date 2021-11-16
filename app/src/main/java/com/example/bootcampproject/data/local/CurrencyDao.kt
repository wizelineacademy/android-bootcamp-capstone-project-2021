package com.example.bootcampproject.data.local

import androidx.room.*
import com.example.bootcampproject.domain.Currency

@Dao
interface CurrencyDao {
    @Query("Select * From Currency")
    suspend fun getAll():List<Currency>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(currencies: MutableList<Currency>)

    @Update
    suspend fun updateAll(currencies: MutableList<Currency>)

}