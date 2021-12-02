package dev.ricsarabia.cryptochallenge.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.ricsarabia.cryptochallenge.domain.RefreshTime
import kotlinx.coroutines.flow.Flow

/**
 * Created by Ricardo Sarabia on 2021/12/02.
 */
@Dao
interface RefreshTimeDao {
    @Query("SELECT * FROM RefreshTime WHERE book = :book")
    fun findById(book: String): Flow<RefreshTime>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(refreshTime: RefreshTime)
}