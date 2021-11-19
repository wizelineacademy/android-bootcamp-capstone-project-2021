package com.esaudev.wizeline.data.sources

import com.esaudev.wizeline.data.remote.sources.BitsoRemoteDataSource
import com.esaudev.wizeline.model.AvailableBook
import com.esaudev.wizeline.model.OrderBook
import com.esaudev.wizeline.model.Ticker
import com.esaudev.wizeline.repository.BitsoRepository
import com.esaudev.wizeline.utils.DataState

class FakeBitsoSuccessRepository: BitsoRepository {

    private val books = listOf<AvailableBook>(
        AvailableBook(
            book = "btc_mxn",
            minimumAmount = ".003",
            maximumAmount = "1000.00",
            minimumPrice = "100.00",
            maximumPrice = "1000000.00",
            minimumValue = "25.00",
            maximumValue = "1000000.00",
            icon = "https://cryptoicon-api.vercel.app/api/icon/btc"
        )
    )

    override suspend fun getAvailableBooks(): DataState<List<AvailableBook>> {
        return DataState.Success(books)
    }

    override suspend fun getTickerFromBook(book: String): DataState<Ticker> {
        return DataState.Success(
            Ticker(
                ask = "ask",
                bid = "bid",
                book = book,
                created_at = "created_at",
                high = "high",
                last = "last",
                low = "low",
                volume = "volume",
                vwap = "vwap"
            )
        )
    }

    override suspend fun getOrderBook(book: String): DataState<OrderBook> {
        return DataState.Success(
            OrderBook(
                asks = listOf(),
                bids = listOf(),
                sequence = "sequence",
                updated_at = "updated_at"
            )
        )
    }
}