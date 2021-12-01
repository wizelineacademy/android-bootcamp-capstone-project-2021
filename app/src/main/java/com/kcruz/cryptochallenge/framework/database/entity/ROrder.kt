package com.kcruz.cryptochallenge.framework.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "order")
data class ROrder(
    @ColumnInfo(name = "book") val book: String,
    @ColumnInfo(name = "price") val price: String,
    @ColumnInfo(name = "amount") val amount: String,
    @ColumnInfo(name = "type") var type: String,
    @PrimaryKey val oid: String
)