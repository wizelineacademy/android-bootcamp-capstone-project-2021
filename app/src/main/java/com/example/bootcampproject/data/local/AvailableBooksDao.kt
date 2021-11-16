package com.example.bootcampproject.data.local

import androidx.room.*
import com.example.bootcampproject.data.mock.AvailableBook


@Dao
interface AvailableBooksDao {
    @Query("Select * From AvailableBook")
    suspend fun getAll():List<AvailableBook>

    @Query("Select * From AvailableBook Where book Like :book || '%'")
    suspend fun getSelectedBooks(book:String?):List<AvailableBook>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(currencies: MutableList<AvailableBook>)

    @Update
    suspend fun updateAll(currencies: MutableList<AvailableBook>)
}