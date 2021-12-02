package com.example.cryptochallenge.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "books")
data class Book(
    @PrimaryKey(autoGenerate = true)
    val bookId: Long? = null,
    @SerializedName("book") val name: String,
    val image: String,
    val last: String = "0",
    val high: String = "0",
    val low: String = "0",
)