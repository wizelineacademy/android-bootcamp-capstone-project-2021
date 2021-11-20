package dev.ricsarabia.cryptochallenge.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.ricsarabia.cryptochallenge.domain.BookOrder
import kotlinx.coroutines.flow.Flow

/**
 * Created by Ricardo Sarabia on 2021/11/20.
 */
@Dao
interface BookOrderDao {
    @Query("SELECT * FROM BookOrder WHERE book = :book")
    suspend fun getAllOf(book: String): List<BookOrder>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertBookOrder(bookOrders: List<BookOrder>)
}