package com.esaudev.wizeline.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.esaudev.wizeline.model.Ask
import com.esaudev.wizeline.model.Bid
import java.util.*

@Entity(tableName = "order_books")
data class OrderBookEntity(
    val asks: List<AskEntity>,
    val bids: List<BidEntity>,
    val sequence: String,
    val updated_at: String,
    @PrimaryKey
    val book: String
)
