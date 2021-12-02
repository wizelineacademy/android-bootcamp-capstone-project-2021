package com.example.cryptochallenge.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "asks")
data class Ask(
    @PrimaryKey(autoGenerate = true)
    val askId: Long? = null,
    val price: String,
    val amount: String,
    var bookName: String
)