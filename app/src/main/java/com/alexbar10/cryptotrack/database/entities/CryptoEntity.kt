package com.alexbar10.cryptotrack.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cryptos")
data class CryptoEntity(
    @PrimaryKey
    val book: String,
    val high: Double?,
    val last: Double?,
    val low: Double?
)
