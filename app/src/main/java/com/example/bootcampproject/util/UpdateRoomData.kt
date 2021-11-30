package com.example.bootcampproject.util

import com.example.bootcampproject.data.local.AvailableBooksDao
import com.example.bootcampproject.data.local.CurrencyDao
import com.example.bootcampproject.data.local.OrderBookDao
import com.example.bootcampproject.data.local.TickerDao
import com.example.bootcampproject.data.mock.StatusAvailableBooks
import com.example.bootcampproject.data.mock.StatusOrderBook
import com.example.bootcampproject.data.mock.StatusTicker
import com.example.bootcampproject.data.services.BitsoServicesObservable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class UpdateRoomData @Inject constructor(

    private val bitsoServicesObservable: BitsoServicesObservable,
    private val currencyDao: CurrencyDao,
    private val availableBooksDao: AvailableBooksDao,
    private val orderBookDao: OrderBookDao,
    private val tickerDao: TickerDao
) {
    fun startObservables() {
        runningObservables(bitsoServicesObservable.getAvailableBooks(), "")
    }

    private fun runningObservables(observable: Observable<*>, opcCode: String) {
        observable.subscribeOn(Schedulers.io())
            .delay(3, TimeUnit.SECONDS)
            .observeOn(Schedulers.io())
            //.retryWhen { errors -> errors.flatMap { error -> } }
            .subscribe { body ->
                when (body) {
                    is StatusAvailableBooks -> insertCurrencyAndAvailableBook(body)
                    is StatusOrderBook -> insertOrderBook(body, opcCode)
                    is StatusTicker -> insertTickers(body, opcCode)
                }
            }
    }

    private fun insertCurrencyAndAvailableBook(availableBooks: StatusAvailableBooks) {
        currencyDao.insertAll(availableBooks.getCurrencies())
        availableBooksDao.insertAll(availableBooks.payload)

        availableBooks.payload.forEach { availableBook ->
            runningObservables(
                bitsoServicesObservable.getOrderBook(availableBook.book),
                availableBook.book
            )
            runningObservables(
                bitsoServicesObservable.getTicker(availableBook.book),
                availableBook.book
            )
        }
    }

    private fun insertOrderBook(orderBooks: StatusOrderBook, code: String) {
        orderBooks.payload.book = code
        orderBookDao.insert(orderBooks.payload)
    }

    private fun insertTickers(tickers: StatusTicker, code: String) {
        tickers.payload.book = code
        tickerDao.insert(tickers.payload)
    }
}
