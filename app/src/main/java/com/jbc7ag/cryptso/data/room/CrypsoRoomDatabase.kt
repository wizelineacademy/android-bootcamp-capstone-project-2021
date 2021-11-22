package com.jbc7ag.cryptso.data.room

import android.content.Context
import androidx.room.*
import com.jbc7ag.cryptso.data.model.*
import com.jbc7ag.cryptso.data.room.dao.BooksDao
import com.jbc7ag.cryptso.data.room.dao.CoinListDao
import com.jbc7ag.cryptso.data.room.dao.OrderDao
import com.jbc7ag.cryptso.data.room.dao.TickerDao
import com.jbc7ag.cryptso.util.DATABASE_NAME


@Database(
    entities = arrayOf(Coins::class, Book::class, OrderDetail::class, BookDetail::class),
    version = 1,
    exportSchema = false
)
@TypeConverters(BidstoCollectionTypeConverter::class)
abstract class CrypsoRoomDatabase : RoomDatabase() {

    abstract fun coinListDao(): CoinListDao
    abstract fun booksDao(): BooksDao
    abstract fun ordersDao(): OrderDao
    abstract fun tickerDao(): TickerDao


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