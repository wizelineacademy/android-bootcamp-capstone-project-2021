package com.example.bootcampproject.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.bootcampproject.data.mock.AvailableBook
import com.example.bootcampproject.domain.Currency

@Database(entities=[Currency::class, AvailableBook::class],version = 5)
abstract class BitsoAppDataBase : RoomDatabase() {
    abstract fun getCurrencyDao():CurrencyDao
    abstract fun getAvailableBooks():AvailableBooksDao
}