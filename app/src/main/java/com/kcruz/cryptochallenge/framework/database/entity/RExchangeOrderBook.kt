package com.kcruz.cryptochallenge.framework.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exchange_order_book")
data class RExchangeOrderBook(
    @PrimaryKey val book: String,
    @ColumnInfo(name = "minimum_amount") val minimumAmount: String,
    @ColumnInfo(name = "maximum_amount") val maximumAmount: String,
    @ColumnInfo(name = "minimum_price") val minimumPrice: String,
    @ColumnInfo(name = "maximum_price") val maximumPrice: String,
    @ColumnInfo(name = "minimum_value") val minimumValue: String,
    @ColumnInfo(name = "maximum_value") val maximumValue: String
)