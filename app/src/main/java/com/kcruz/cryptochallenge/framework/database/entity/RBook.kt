package com.kcruz.cryptochallenge.framework.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "book")
data class RBook(
    @PrimaryKey val book: String,
    @ColumnInfo(name = "volume") val volume: String,
    @ColumnInfo(name = "high") val high: String,
    @ColumnInfo(name = "last") val last: String,
    @ColumnInfo(name = "low") val low: String,
    @ColumnInfo(name = "vwap") val vwap: String,
    @ColumnInfo(name = "ask") val ask: String,
    @ColumnInfo(name = "bid") val bid: String,
    @ColumnInfo(name = "created_at") val createdAt: String
)