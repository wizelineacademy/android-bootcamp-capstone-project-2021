package dev.ricsarabia.cryptochallenge.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.ricsarabia.cryptochallenge.domain.Book
import kotlinx.coroutines.flow.Flow

/**
 * Created by Ricardo Sarabia on 2021/11/20.
 */
@Dao
interface BookDao {
    @Query("SELECT * FROM Book ORDER BY major, minor")
    fun getAll(): Flow<List<Book>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(books: List<Book>)
}