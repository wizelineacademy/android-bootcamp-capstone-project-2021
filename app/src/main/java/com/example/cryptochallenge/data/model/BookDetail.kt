package com.example.cryptochallenge.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books_details")
data class BookDetail (
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
    val book: String,
    val image: String,
    val last: String,
    val high: String,
    val low: String
)