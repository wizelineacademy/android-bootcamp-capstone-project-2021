package com.esaudev.wizeline.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.esaudev.wizeline.data.local.entities.AvailableBookEntity
import com.esaudev.wizeline.data.local.entities.OrderBookEntity
import com.esaudev.wizeline.data.local.entities.TickerEntity

@Database(
    entities = [AvailableBookEntity::class, OrderBookEntity::class, TickerEntity::class],
    version = 1
)

@TypeConverters(RoomConverters::class)
abstract class BitsoDatabase: RoomDatabase(){

    abstract fun bitsoDao(): BitsoDao
}