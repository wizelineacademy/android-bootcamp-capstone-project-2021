package com.example.bootcampproject.data.local

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.bootcampproject.data.mock.DateUpdate
import com.example.bootcampproject.domain.Currency

interface DateUpdateDao {
    @Query("Select * From DateUpdate")
    suspend fun getAll():List<DateUpdate>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(currencies: DateUpdate)

    @Update
    suspend fun updateAll(currencies: DateUpdate)
}