package com.kcruz.cryptochallenge.framework.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.kcruz.cryptochallenge.framework.database.dao.IExchangeOrderBookDao
import com.kcruz.cryptochallenge.framework.database.entity.RExchangeOrderBook

@Database(
    entities = [RExchangeOrderBook::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase: RoomDatabase() {
    abstract val exchangeOrderBookDao: IExchangeOrderBookDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "currencies_database"
                    ).build()
                }

                return instance
            }
        }
    }
}