package com.example.cryptochallenge.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.cryptochallenge.data.model.Ask

@Dao
interface AskDAO {
    @Query("SELECT * FROM asks WHERE bookName=:bookName")
    fun getAllAsksByBookName(bookName: String): List<Ask>

    @Insert
    fun insertAsk(ask: Ask)

    @Query("DELETE FROM asks WHERE bookName=:bookName")
    fun deleteAsksByBookName(bookName: String)
}