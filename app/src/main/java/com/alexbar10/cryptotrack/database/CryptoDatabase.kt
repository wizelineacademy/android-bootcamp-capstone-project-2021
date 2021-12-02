package com.alexbar10.cryptotrack.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.alexbar10.cryptotrack.database.entities.CryptoEntity
import com.alexbar10.cryptotrack.database.entities.OrderEntity

@Database(entities = [CryptoEntity::class, OrderEntity::class], version = 2, exportSchema = false)
abstract class CryptoDatabase : RoomDatabase() {
    abstract fun cryptoDatabaseDao(): CryptoDao
}
