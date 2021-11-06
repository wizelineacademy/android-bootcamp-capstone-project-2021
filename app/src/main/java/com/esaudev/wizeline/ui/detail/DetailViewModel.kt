package com.esaudev.wizeline.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.esaudev.wizeline.model.AvailableBook
import com.esaudev.wizeline.model.OrderBook
import com.esaudev.wizeline.model.Ticker
import com.esaudev.wizeline.repository.BitsoRepository
import com.esaudev.wizeline.utils.Constants
import com.esaudev.wizeline.utils.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: BitsoRepository
): ViewModel() {

    private var _getOrderBooks = MutableLiveData<DataState<OrderBook>>()
    val getOrderBooks: LiveData<DataState<OrderBook>> = _getOrderBooks

    fun getOrderBooks(book: String){
        viewModelScope.launch {
            try{
                _getOrderBooks.value = DataState.Loading
                val data = repository.getOrderBook(book)
                _getOrderBooks.value = data
            } catch (e: Exception) {
                _getOrderBooks.value = DataState.Error(Constants.NETWORK_UNKNOWN_ERROR)
            }
        }
    }

    private var _getTicker = MutableLiveData<DataState<Ticker>>()
    val getTicker: LiveData<DataState<Ticker>> = _getTicker

    fun getTickerFromBook(book: String){
        viewModelScope.launch {
            try {
                _getTicker.value = DataState.Loading
                val data = repository.getTickerFromBook(book)
                _getTicker.value = data
            } catch (e: Exception) {
                _getTicker.value = DataState.Error(Constants.NETWORK_UNKNOWN_ERROR)
            }
        }
    }
}