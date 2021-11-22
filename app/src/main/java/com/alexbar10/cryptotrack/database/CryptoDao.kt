package com.alexbar10.cryptotrack.database

import androidx.room.*
import com.alexbar10.cryptotrack.database.entities.CryptoEntity
import com.alexbar10.cryptotrack.database.entities.OrderEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CryptoDao {
    // Crypto operations
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCryptos(list: List<CryptoEntity>)

    @Query("SELECT * FROM cryptos")
    fun getAvailableCryptos(): Flow<List<CryptoEntity>>

    @Query("SELECT * FROM cryptos WHERE book = :book")
    fun getCryptoBy(book: String): Flow<CryptoEntity?>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(crypto: CryptoEntity)

    // Order operations
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrders(list: List<OrderEntity>)

    @Query("SELECT * FROM orders WHERE book = :book")
    fun getOrdersFor(book: String): Flow<List<OrderEntity>>

    @Query("DELETE FROM orders WHERE book = :book")
    suspend fun deleteOrdersFor(book: String)
}