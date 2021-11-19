package com.jbc7ag.cryptso.ui.currencydetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jbc7ag.cryptso.data.model.BookDetail
import com.jbc7ag.cryptso.data.model.OrderDetail
import com.jbc7ag.cryptso.data.repository.CurrencyRepository
import com.jbc7ag.cryptso.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CurrencyViewModel @Inject constructor(
    private val currencyRepository: CurrencyRepository
) : ViewModel() {

    private val _bookTicker = MutableLiveData<BookDetail>()
    val bookTicker: LiveData<BookDetail>
        get() = _bookTicker

    private val _orders = MutableLiveData<OrderDetail>()
    val orders: LiveData<OrderDetail>
        get() = _orders

    private val _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error

    private var _loadingOrders = MutableLiveData<Boolean>()
    val loadingOrders: LiveData<Boolean>
        get() = _loadingOrders


    private var _loadingTicker = MutableLiveData<Boolean>()
    val loadingTicker: LiveData<Boolean>
        get() = _loadingTicker


    fun downloadOrders(book: String) = viewModelScope.launch() {
        try {

            _loadingOrders.value = true
            val result = withContext(Dispatchers.IO) { currencyRepository.downloadOrders(book) }
            when (result) {
                is Resource.Success -> {
                    result.data?.let {
                        withContext(Dispatchers.IO) {
                            it.book = book
                            currencyRepository.saveOrder(it)
                        }
                    }
                }
                is Resource.Error -> {
                    _error.value = result.message
                }
            }
        } catch (ex: Exception) {
            _error.value = ex.localizedMessage
        }
        _loadingOrders.value = false
    }

    fun getOrder(book: String) = viewModelScope.launch(Dispatchers.IO) {
        val result = currencyRepository.getOrder(book)
        withContext(Dispatchers.Main) {
            _orders.value = result
        }
    }


    fun downloadTicker(book: String) = viewModelScope.launch() {
        try {

            _loadingTicker.value = true
            val result = withContext(Dispatchers.IO) { currencyRepository.downloadTicker(book) }
            when (result) {
                is Resource.Success -> {
                    result.data?.let {
                        withContext(Dispatchers.IO) {
                            currencyRepository.saveTicker(it)
                        }
                    }
                }
                is Resource.Error -> {
                    _error.value = result.message
                }
            }
        } catch (ex: Exception) {
            _error.value = ex.localizedMessage
        }
        _loadingTicker.value = false
    }

    fun getTicker(book: String) = viewModelScope.launch(Dispatchers.IO) {
        val result = currencyRepository.getTicker(book)
        withContext(Dispatchers.Main) {
            _bookTicker.value = result
        }
    }
}