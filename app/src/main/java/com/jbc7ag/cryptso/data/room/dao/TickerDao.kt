package com.jbc7ag.cryptso.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jbc7ag.cryptso.data.model.BookDetail

@Dao
interface TickerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entity: BookDetail)

    @Query("DELETE FROM ticker WHERE book=:book")
    fun delete(book: String)

    @Query("SELECT * FROM ticker WHERE book=:book")
    fun getTicker(book: String): BookDetail
}
