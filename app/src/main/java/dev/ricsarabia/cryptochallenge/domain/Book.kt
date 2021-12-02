package dev.ricsarabia.cryptochallenge.domain

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Book(
    @PrimaryKey val book: String,
    val major: String,
    val minor: String,
    val imageUrl: String
)
