package com.esaudev.wizeline.data.local.entities

import androidx.room.Entity
import java.util.UUID

@Entity(tableName = "asks")
data class AskEntity(
    val amount: String,
    val book: String,
    val price: String,
    val id: String = UUID.randomUUID().toString()
)
