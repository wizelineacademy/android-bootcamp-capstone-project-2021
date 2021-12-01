package com.example.bootcampproject.ui.orderbooks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bootcampproject.data.mock.OrderBook
import com.example.bootcampproject.data.mock.Ticker
import com.example.bootcampproject.data.repo.OrderBookRepo
import com.example.bootcampproject.data.repo.TickerRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderBooksViewModel @Inject constructor(
    private val orderBooksRepo: OrderBookRepo,
    private val tickerRepo: TickerRepo
) : ViewModel() {
    private val _orderbooks: MutableLiveData<OrderBook> = MutableLiveData()
    val orderbooks: LiveData<OrderBook> = _orderbooks

    private val _tickers: MutableLiveData<Ticker> = MutableLiveData()
    val tickers: LiveData<Ticker> = _tickers

    fun getActualCurrencies(code: String?, isConnected: Boolean) {
        CoroutineScope(Dispatchers.IO).launch {
            _orderbooks.postValue(orderBooksRepo.getOrderBooks(code, isConnected))
        }
    }

    fun getActualTicker(code: String?, isConnected: Boolean) {
        CoroutineScope(Dispatchers.IO).launch {
            _tickers.postValue(tickerRepo.getTicker(code, isConnected))
        }
    }
}
