package dev.ricsarabia.cryptochallenge.domain

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BookPrices(
    @PrimaryKey val book: String,
    val last: String,
    val high: String,
    val low: String
)
