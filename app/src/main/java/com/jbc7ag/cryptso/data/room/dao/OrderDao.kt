package com.jbc7ag.cryptso.data.room.dao

import androidx.room.*
import com.jbc7ag.cryptso.data.model.OrderDetail

@Dao
interface OrderDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entity: OrderDetail)

    @Query("DELETE FROM orders WHERE book=:book")
    fun delete(book: String)

    @Query("SELECT * FROM orders WHERE book=:book")
    fun getOrder(book: String): OrderDetail
}