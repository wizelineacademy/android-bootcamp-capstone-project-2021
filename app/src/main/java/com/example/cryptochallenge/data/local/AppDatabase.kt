package com.example.cryptochallenge.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cryptochallenge.data.model.Ask
import com.example.cryptochallenge.data.model.Bid
import com.example.cryptochallenge.data.model.Book

@Database(entities = [Book::class, Ask::class, Bid::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun bookDao(): BookDAO
    abstract fun askDao(): AskDAO
    abstract fun bidDao(): BidDAO
}