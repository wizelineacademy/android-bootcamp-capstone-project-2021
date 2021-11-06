package com.kcruz.cryptochallenge.presentation.currencies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kcruz.cryptochallenge.App
import com.kcruz.cryptochallenge.R
import com.kcruz.cryptochallenge.data.repository.BookRepository
import com.kcruz.cryptochallenge.domain.ExchangeOrderBook
import com.kcruz.cryptochallenge.framework.response.AvailableBooksResponse
import com.kcruz.cryptochallenge.framework.source.remote.BookRemoteSource
import com.kcruz.cryptochallenge.presentation.commons.SingleLiveEvent
import com.kcruz.cryptochallenge.usecase.GetAvailableBooks
import kotlinx.coroutines.launch

class CurrenciesViewModel: ViewModel() {

    private val _availableBooks = MutableLiveData<List<ExchangeOrderBook>> ()
    val availableBooks: LiveData<List<ExchangeOrderBook>>
        get() = _availableBooks

    private val _events = SingleLiveEvent<CurrenciesEvent> ()
    val events: LiveData<CurrenciesEvent>
        get() = _events

    init {
        getAvailableBooks()
    }

    private fun getAvailableBooks() {
        viewModelScope.launch {
            //TODO: Add dependency injection library
            val response = GetAvailableBooks(BookRepository(BookRemoteSource())).getAvailableBooks()

            if (response.body is AvailableBooksResponse && response.body.success) {
                _availableBooks.value = response.body.payload
            } else {
                _events.value = SendMessage(App.appContext.getString(R.string.error_message))
            }

        }
    }
}