package com.jbc7ag.cryptso.data.room.dao

import androidx.room.*
import com.jbc7ag.cryptso.data.model.Book

@Dao
interface BooksDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entity: List<Book>)

    @Update
    fun update(entity: Book)

    @Query("DELETE FROM book")
    fun delete()

    @Query("SELECT book.id, book.book, coin_list.id as name FROM book LEFT JOIN coin_list ON substr(book.book, 0, INSTR(book.book,'_')) = coin_list.symbol")
    fun getBooks(): List<Book>
}