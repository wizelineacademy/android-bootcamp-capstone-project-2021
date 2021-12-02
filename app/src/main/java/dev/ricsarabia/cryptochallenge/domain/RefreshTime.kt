package dev.ricsarabia.cryptochallenge.domain

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.OffsetDateTime

/**
 * Created by Ricardo Sarabia on 2021/12/02.
 */
@Entity
data class RefreshTime(
    @PrimaryKey val book: String,
    val dateTime: String
)
