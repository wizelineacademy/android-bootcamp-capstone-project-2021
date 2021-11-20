package com.kcruz.cryptochallenge.framework.database.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kcruz.cryptochallenge.framework.database.entity.RExchangeOrderBook

interface IExchangeOrderBookDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(exchangeOrder: RExchangeOrderBook)

    @Query("SELECT * FROM exchange_order_book")
    fun getExchangeOrderBooks(): List<RExchangeOrderBook>

}