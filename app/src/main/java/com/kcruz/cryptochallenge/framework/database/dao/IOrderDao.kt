package com.kcruz.cryptochallenge.framework.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kcruz.cryptochallenge.framework.database.entity.ROrder

@Dao
interface IOrderDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveOrders(orders: List<ROrder>)

    @Query("SELECT * FROM `order` WHERE book = :book AND type = :type")
    fun getOrders(book: String, type: String): List<ROrder>
}