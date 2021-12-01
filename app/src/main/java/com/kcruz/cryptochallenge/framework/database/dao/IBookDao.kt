package com.kcruz.cryptochallenge.framework.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.kcruz.cryptochallenge.framework.database.entity.RBook

@Dao
interface IBookDao {

    @Query("SELECT * FROM book WHERE book = :bookId")
    fun getBook(bookId: String): RBook?

    @Insert(onConflict = REPLACE)
    fun saveBook(book: RBook)
}