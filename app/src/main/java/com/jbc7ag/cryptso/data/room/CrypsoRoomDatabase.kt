package com.jbc7ag.cryptso.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jbc7ag.cryptso.data.model.AvailableBooks
import com.jbc7ag.cryptso.data.model.Book
import com.jbc7ag.cryptso.data.model.Coins
import com.jbc7ag.cryptso.data.room.dao.BooksDao
import com.jbc7ag.cryptso.data.room.dao.CoinListDao
import com.jbc7ag.cryptso.util.DATABASE_NAME


@Database(entities = arrayOf(Coins::class, Book::class), version = 1, exportSchema = false)
abstract class CrypsoRoomDatabase : RoomDatabase() {

    abstract fun coinListDao(): CoinListDao
    abstract fun booksDao(): BooksDao


    companion object {
        @Volatile
        private var INSTANCE: CrypsoRoomDatabase? = null

        fun getDatabase(context: Context): CrypsoRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CrypsoRoomDatabase::class.java,
                    DATABASE_NAME
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}