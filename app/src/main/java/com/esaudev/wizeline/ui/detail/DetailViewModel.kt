package com.esaudev.wizeline.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.esaudev.wizeline.model.OrderBook
import com.esaudev.wizeline.model.Ticker
import com.esaudev.wizeline.repository.BitsoRepository
import com.esaudev.wizeline.utils.Constants
import com.esaudev.wizeline.utils.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: BitsoRepository
) : ViewModel() {

    private var _getOrderBookState = MutableLiveData<DataState<OrderBook>>()
    val getOrderBookState: LiveData<DataState<OrderBook>> = _getOrderBookState

    private var _getTickerState = MutableLiveData<DataState<Ticker>>()
    val getTickerState: LiveData<DataState<Ticker>> = _getTickerState

    fun getViewData(book: String) {
        viewModelScope.launch {

            _getTickerState.value = DataState.Loading
            _getOrderBookState.value = DataState.Loading

            val ticker: Deferred<DataState<Ticker>> = async {
                getTicker(book = book)
            }

            val books: Deferred<DataState<OrderBook>> = async {
                getBooks(book = book)
            }

            _getTickerState.value = ticker.await()
            _getOrderBookState.value = books.await()
        }
    }

    private suspend fun getTicker(book: String): DataState<Ticker>{
        return repository.getTickerFromBook(book = book)
    }

    private suspend fun getBooks(book: String): DataState<OrderBook>{
        return repository.getOrderBook(book = book)
    }

    fun getOrderBooks(book: String) {
        viewModelScope.launch {
            try {
                _getOrderBookState.value = DataState.Loading
                val data = repository.getOrderBook(book)
                _getOrderBookState.value = data
            } catch (e: Exception) {
                _getOrderBookState.value = DataState.Error(Constants.NETWORK_UNKNOWN_ERROR)
            }
        }
    }

    fun getTickerFromBook(book: String) {
        viewModelScope.launch {
            try {
                _getTickerState.value = DataState.Loading
                val data = repository.getTickerFromBook(book)
                _getTickerState.value = data
            } catch (e: Exception) {
                _getTickerState.value = DataState.Error(Constants.NETWORK_UNKNOWN_ERROR)
            }
        }
    }
}
