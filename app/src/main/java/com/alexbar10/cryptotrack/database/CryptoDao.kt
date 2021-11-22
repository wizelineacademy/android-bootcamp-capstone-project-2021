package com.alexbar10.cryptotrack.database

import androidx.room.*
import com.alexbar10.cryptotrack.database.entities.CryptoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CryptoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCryptos(list: List<CryptoEntity>)

    @Query("SELECT * FROM cryptos")
    fun getAvailableCryptos(): Flow<List<CryptoEntity>>

    @Query("SELECT * FROM cryptos WHERE book = :book")
    fun getCryptoBy(book: String): Flow<CryptoEntity?>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(crypto: CryptoEntity)
}