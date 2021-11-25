package com.jbc7ag.cryptso.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "coin_list")
data class Coins(
    @PrimaryKey @ColumnInfo(name = "id")
    val id: String,
    val symbol: String,
    val name: String
)
