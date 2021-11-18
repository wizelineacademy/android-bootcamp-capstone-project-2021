package com.esaudev.wizeline.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books")
data class AvailableBookEntity(
    @PrimaryKey
    val book: String,
    val maximumAmount: String,
    val maximumPrice: String,
    val maximumValue: String,
    val minimumAmount: String,
    val minimumPrice: String,
    val minimumValue: String,
    val icon: String
)