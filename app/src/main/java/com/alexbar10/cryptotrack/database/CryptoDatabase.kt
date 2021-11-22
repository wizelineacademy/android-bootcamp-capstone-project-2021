package com.alexbar10.cryptotrack.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.alexbar10.cryptotrack.database.entities.CryptoEntity

@Database(entities = [CryptoEntity::class], version = 1, exportSchema = false)
abstract class CryptoDatabase: RoomDatabase() {
    abstract fun cryptoDatabaseDao(): CryptoDao
}