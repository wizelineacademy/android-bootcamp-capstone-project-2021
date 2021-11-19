package com.example.bootcampproject.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.bootcampproject.data.mock.AvailableBook
import com.example.bootcampproject.data.mock.OrderBook
import com.example.bootcampproject.data.mock.Ticker
import com.example.bootcampproject.domain.Currency
import com.example.bootcampproject.util.Converter
import com.example.bootcampproject.util.ConverterAsk
import com.example.bootcampproject.util.ConverterBids

@Database(
    entities = [Currency::class, AvailableBook::class,
        OrderBook::class, Ticker::class], version = 9
)
@TypeConverters(ConverterAsk::class, ConverterBids::class)
abstract class BitsoAppDataBase : RoomDatabase() {
    abstract fun getCurrencyDao(): CurrencyDao
    abstract fun getAvailableBooks(): AvailableBooksDao
    abstract fun getOrderBooks(): OrderBookDao
    abstract fun getTickers(): TickerDao
}