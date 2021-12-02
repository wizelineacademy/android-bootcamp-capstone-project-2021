package com.alexbar10.cryptotrack.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "orders")
data class OrderEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val book: String,
    val price: Double,
    val amount: Double,
    val type: Int
)
