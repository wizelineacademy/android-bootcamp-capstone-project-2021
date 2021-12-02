package dev.ricsarabia.cryptochallenge.data.db

import androidx.room.*
import dev.ricsarabia.cryptochallenge.domain.BookOrder
import kotlinx.coroutines.flow.Flow

/**
 * Created by Ricardo Sarabia on 2021/11/20.
 */
@Dao
interface BookOrderDao {
    @Query("SELECT * FROM BookOrder WHERE book = :book AND type = :orderType")
    fun getAllOf(book: String, orderType: BookOrder.Type): Flow<List<BookOrder>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(bookOrders: List<BookOrder>)

    @Query("DELETE FROM BookOrder WHERE book = :book")
    suspend fun deleteOldOrdersOf(book: String)
}
