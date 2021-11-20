package dev.ricsarabia.cryptochallenge.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.ricsarabia.cryptochallenge.domain.BookPrices

/**
 * Created by Ricardo Sarabia on 2021/11/20.
 */
@Dao
interface BookPricesDao {
    @Query("SELECT * FROM BookPrices WHERE book = :book ")
    suspend fun findById(book: String): BookPrices

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(bookPrices: List<BookPrices>)
}