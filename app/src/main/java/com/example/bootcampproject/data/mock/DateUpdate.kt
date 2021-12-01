package com.example.bootcampproject.data.mock

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class DateUpdate(
    @PrimaryKey val id: Int,
    val lastTimeUpdate: Date?
)
