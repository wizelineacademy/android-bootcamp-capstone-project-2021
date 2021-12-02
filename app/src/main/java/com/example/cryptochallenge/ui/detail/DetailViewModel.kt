package com.example.cryptochallenge.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptochallenge.common.EMPTY_STRING
import com.example.cryptochallenge.data.model.*
import com.example.cryptochallenge.domain.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject



@HiltViewModel
class DetailViewModel @Inject constructor(
    private val bookRepository: BookRepository
): ViewModel() {
    private var _getBookDetailEvent = MutableLiveData<GetBookDetailEvent>()
    val getBookDetailEvent: LiveData<GetBookDetailEvent> = _getBookDetailEvent
    private var _getBookOrdersEvent = MutableLiveData<GetBookOrdersEvent>()
    val getBookOrdersEvent: LiveData<GetBookOrdersEvent> = _getBookOrdersEvent
    private val _bookName = MutableLiveData(EMPTY_STRING)

    fun setBookName(name: String) {
        _bookName.value = name
    }

    fun getBookDetail() {
        Log.e("sdf", "Get book detail " + _bookName.value)
        viewModelScope.launch {
            try {
                val book = bookRepository.getBookDetails(_bookName.value ?: EMPTY_STRING)
                _getBookDetailEvent.value = GetBookDetailSuccess(book)
                Log.e("sdf", "Get book detail HIGH" + book.high + " " + book.name)

            } catch (e: Exception) {
                _getBookDetailEvent.value = GetBookDetailFailure(e)
            }
        }
    }
//
    fun getBookOrders() {
        viewModelScope.launch {
            try {
                val data = bookRepository.getBookOrders(_bookName.value ?: EMPTY_STRING)
                _getBookOrdersEvent.value = GetBookOrdersSuccess(data)
            } catch (e: Exception) {
                _getBookOrdersEvent.value = GetBookOrdersFailure(e)
            }
        }
    }
}



































