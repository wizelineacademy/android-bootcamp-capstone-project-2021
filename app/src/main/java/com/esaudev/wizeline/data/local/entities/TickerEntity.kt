package com.esaudev.wizeline.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tickers")
data class TickerEntity (
    val ask: String,
    val bid: String,
    @PrimaryKey
    val book: String,
    val created_at: String,
    val high: String,
    val last: String,
    val low: String,
    val volume: String,
    val vwap: String
        )