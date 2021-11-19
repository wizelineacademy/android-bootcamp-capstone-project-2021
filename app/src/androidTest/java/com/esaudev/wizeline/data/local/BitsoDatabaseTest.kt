package com.esaudev.wizeline.data.local

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.esaudev.wizeline.data.local.entities.AvailableBookEntity
import com.esaudev.wizeline.data.local.entities.OrderBookEntity
import com.esaudev.wizeline.data.local.entities.TickerEntity
import com.esaudev.wizeline.model.OrderBook
import com.google.common.truth.Truth.assertThat
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BitsoDatabaseTest: TestCase() {

    private lateinit var bitsoDatabase: BitsoDatabase
    private lateinit var bitsoDao: BitsoDao

    @Before
    public override fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        bitsoDatabase = Room.inMemoryDatabaseBuilder(context, BitsoDatabase::class.java).build()
        bitsoDao = bitsoDatabase.bitsoDao()
    }

    @After
    fun closeDatabase(){
        bitsoDatabase.close()
    }

    @Test
    fun writeAndReadAvailableBooks() = runBlocking {
        val availableBook = AvailableBookEntity(
            book = "btc_mxn",
            minimumAmount = ".003",
            maximumAmount = "1000.00",
            minimumPrice = "100.00",
            maximumPrice = "1000000.00",
            minimumValue = "25.00",
            maximumValue = "1000000.00",
            icon = "https://cryptoicon-api.vercel.app/api/icon/btc"
        )

        bitsoDao.insertBook(availableBook)
        val availableBooks = bitsoDao.getAllBooks()

        assertThat(availableBooks.contains(availableBook)).isTrue()
    }

    @Test
    fun writeAndReadTickerFromBook() = runBlocking {
        val ticker = TickerEntity(
            ask = "ask",
            bid = "bid",
            book = "book",
            created_at = "created_at",
            high = "high",
            last = "last",
            low = "low",
            volume = "volume",
            vwap = "vwap"
        )

        bitsoDao.insertBookTicker(ticker)
        val tickerFromBook = bitsoDao.getTickerFromBook(ticker.book)

        assertThat(tickerFromBook).isEqualTo(ticker)
    }

    @Test
    fun writeAndReadOrderFromBook() = runBlocking {
        val orderBook = OrderBookEntity(
            asks = listOf(),
            bids = listOf(),
            sequence = "sequence",
            updated_at = "updated_at",
            book = "book"
        )

        bitsoDao.insertOrderBook(orderBook)
        val orderBookFromBook = bitsoDao.getOrderBookByBook(orderBook.book)

        assertThat(orderBookFromBook).isEqualTo(orderBook)
    }
}