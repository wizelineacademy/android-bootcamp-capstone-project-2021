package com.kcruz.cryptochallenge.presentation.currencies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kcruz.cryptochallenge.App
import com.kcruz.cryptochallenge.R
import com.kcruz.cryptochallenge.data.repository.BookRepository
import com.kcruz.cryptochallenge.domain.ExchangeOrderBook
import com.kcruz.cryptochallenge.framework.database.AppDatabase
import com.kcruz.cryptochallenge.framework.response.AvailableBooksResponse
import com.kcruz.cryptochallenge.framework.source.local.BookLocalSource
import com.kcruz.cryptochallenge.framework.source.remote.BookRemoteSource
import com.kcruz.cryptochallenge.presentation.commons.SingleLiveEvent
import com.kcruz.cryptochallenge.usecase.GetAvailableBooks
import kotlinx.coroutines.launch

//TODO: Add documentation

class CurrenciesViewModel : ViewModel() {

    private val _availableBooks = MutableLiveData<List<ExchangeOrderBook>>()
    val availableBooks: LiveData<List<ExchangeOrderBook>>
        get() = _availableBooks

    private val _events = SingleLiveEvent<CurrenciesEvent>()
    val events: LiveData<CurrenciesEvent>
        get() = _events

    fun start() {
        getAvailableBooks()
    }

    private fun getAvailableBooks() {
        viewModelScope.launch {
            //TODO: Add dependency injection library
            val response = GetAvailableBooks(
                BookRepository(
                    BookRemoteSource(), BookLocalSource(
                        AppDatabase.getInstance(App.appContext)
                    )
                )
            ).getAvailableBooks()

            if (response.code == 200) {
                _availableBooks.value = response.body as List<ExchangeOrderBook>?
            } else {
                _events.value = SendMessage(App.appContext.getString(R.string.error_message))
            }

        }
    }
}