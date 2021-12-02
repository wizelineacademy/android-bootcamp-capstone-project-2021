package com.example.cryptochallenge.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.cryptochallenge.data.model.Book
import com.example.cryptochallenge.data.model.GetBooks

@Dao
interface BookDAO {
    @Query("SELECT * FROM books")
    fun getAllBooks(): List<Book>

    @Query("UPDATE books SET last=:last, high=:high, low=:low WHERE name=:name")
    fun updateBookByName(last: String, high: String, low: String, name: String)

    @Insert
    fun insertBook(book: Book)

    @Query("DELETE FROM books")
    fun truncateTable()

    @Query("SELECT * FROM books WHERE name=:name")
    fun getBookByName(name: String): Book
}