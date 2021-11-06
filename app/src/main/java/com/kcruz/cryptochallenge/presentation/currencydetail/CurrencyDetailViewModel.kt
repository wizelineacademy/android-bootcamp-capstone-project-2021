package com.kcruz.cryptochallenge.presentation.currencydetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kcruz.cryptochallenge.App
import com.kcruz.cryptochallenge.R
import com.kcruz.cryptochallenge.data.repository.BookRepository
import com.kcruz.cryptochallenge.domain.Book
import com.kcruz.cryptochallenge.framework.response.BookResponse
import com.kcruz.cryptochallenge.framework.source.remote.BookRemoteSource
import com.kcruz.cryptochallenge.presentation.commons.SingleLiveEvent
import com.kcruz.cryptochallenge.usecase.GetBookInfo
import kotlinx.coroutines.launch

//TODO: Add documentation

class CurrencyDetailViewModel: ViewModel() {

    private val _currencyDetail = MutableLiveData<Book> ()
    val currencyDetail: LiveData<Book>
        get() = _currencyDetail

    private val _events = SingleLiveEvent<CurrencyDetailEvent> ()
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
        }
    }

    fun start(book: String) {
        getBookDetail(book)
    }
}