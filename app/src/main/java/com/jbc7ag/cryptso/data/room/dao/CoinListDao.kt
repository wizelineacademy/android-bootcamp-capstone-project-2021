package com.jbc7ag.cryptso.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jbc7ag.cryptso.data.model.Coins

@Dao
interface CoinListDao {

    @Query("SELECT name FROM coin_list WHERE symbol=:symbol")
    fun getCoinListBySymbol(symbol: String): String

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(coinList: Coins)

    @Query("DELETE FROM coin_list")
    suspend fun deleteAll()
}
