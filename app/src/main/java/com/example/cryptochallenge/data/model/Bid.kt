package com.example.cryptochallenge.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bids")
data class Bid(
    @PrimaryKey(autoGenerate = true)
    val bidId: Long? = null,
    val price: String,
    val amount: String,
    var bookName: String
)