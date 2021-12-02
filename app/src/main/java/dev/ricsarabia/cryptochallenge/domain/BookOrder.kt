package dev.ricsarabia.cryptochallenge.domain

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BookOrder(
    val book: String,
    val price: String,
    val amount: String,
    val type: Type,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
) {
    enum class Type { ASK, BID }
}
