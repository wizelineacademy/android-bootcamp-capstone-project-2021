package dev.ricsarabia.cryptochallenge.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.ricsarabia.cryptochallenge.domain.Book
import dev.ricsarabia.cryptochallenge.domain.BookOrder
import dev.ricsarabia.cryptochallenge.domain.BookPrices

/**
 * Created by Ricardo Sarabia on 2021/11/20.
 */
@Database(entities = [Book::class, BookOrder::class, BookPrices::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun bookDao(): BookDao
    abstract fun bookOrderDao(): BookOrderDao
    abstract fun bookPricesDao(): BookPricesDao
}
