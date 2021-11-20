package com.kcruz.cryptochallenge.presentation.currencydetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kcruz.cryptochallenge.App
import com.kcruz.cryptochallenge.R
import com.kcruz.cryptochallenge.data.repository.BookRepository
import com.kcruz.cryptochallenge.domain.Book
import com.kcruz.cryptochallenge.domain.Order
import com.kcruz.cryptochallenge.framework.response.BookResponse
import com.kcruz.cryptochallenge.framework.response.OrderBookResponse
import com.kcruz.cryptochallenge.framework.source.remote.BookRemoteSource
import com.kcruz.cryptochallenge.presentation.commons.SingleLiveEvent
import com.kcruz.cryptochallenge.presentation.currencydetail.order.OrderType
import com.kcruz.cryptochallenge.usecase.GetBookInfo
import com.kcruz.cryptochallenge.usecase.GetOpenOrders
import kotlinx.coroutines.launch

//TODO: Add documentation

class CurrencyDetailViewModel : ViewModel() {

    private val _currencyDetail = MutableLiveData<Book>()
    val currencyDetail: LiveData<Book>
        get() = _currencyDetail

    private val _openOrders = MutableLiveData<Map<String, List<Order>>>()
    val openOrders: LiveData<Map<String, List<Order>>>
        get() = _openOrders

    private val _events = SingleLiveEvent<CurrencyDetailEvent>()
    val events: LiveData<CurrencyDetailEvent>
        get() = _events

    private fun getBookDetail(book: String) {
        viewModelScope.launch {
            //TODO: Add dependency injection library
            val response = GetBookInfo(BookRepository(BookRemoteSource())).getBookInfo(book)
            if (response.body is BookResponse && response.body.success) {
                _currencyDetail.value = response.body.payload
            } else {
                _events.value = SendMessage(App.appContext.getString(R.string.error_message))
            }
            getOpenOrders(book)
        }
    }

    private fun getOpenOrders(book: String) {
        viewModelScope.launch {
            val response =
                GetOpenOrders(BookRepository(BookRemoteSource())).getOpenOrders(book, false)
            if (response.body is OrderBookResponse && response.body.success) {
                val orders = mapOf(
                    OrderType.ASKS.label to response.body.payload.asks,
                    OrderType.BIDS.label to response.body.payload.bids
                )
                _openOrders.value = orders
            } else {
                _events.value = SendMessage(App.appContext.getString(R.string.error_message))
            }

            _events.value = ShowLoading(false)
        }
    }

    fun start(book: String) {
        _events.value = ShowLoading(true)
        getBookDetail(book)
    }
}