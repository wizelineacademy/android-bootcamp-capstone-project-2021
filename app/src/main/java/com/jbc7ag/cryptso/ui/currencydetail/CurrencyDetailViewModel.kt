package com.jbc7ag.cryptso.ui.currencydetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jbc7ag.cryptso.data.model.BookDetail
import com.jbc7ag.cryptso.data.model.OrderDetail
import com.jbc7ag.cryptso.data.repository.CurrencyRepository
import com.jbc7ag.cryptso.util.Resource
import com.jbc7ag.cryptso.util.getCurrencyCode
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@HiltViewModel
class CurrencyDetailViewModel @Inject constructor(
    private val currencyRepository: CurrencyRepository
) : ViewModel() {

    var dispatcher = Dispatchers.IO

    private val _bookTicker = MutableLiveData<BookDetail>()
    val bookTicker: LiveData<BookDetail>
        get() = _bookTicker

    private val _orders = MutableLiveData<OrderDetail?>()
    val orders: LiveData<OrderDetail?>
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

    private var _coinName = MutableLiveData<String>()
    val coinName: LiveData<String>
        get() = _coinName

    fun downloadOrders(book: String) = viewModelScope.launch() {
        try {
            _loadingOrders.value = true
            val result = withContext(dispatcher) { currencyRepository.downloadOrders(book) }
            when (result) {
                is Resource.Success -> {
                    result.data?.let {
                        withContext(dispatcher) {
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
        }finally {
            _loadingOrders.value = false
        }
    }

    fun getOrder(book: String) = viewModelScope.launch(dispatcher) {
        val result = currencyRepository.getOrder(book)
        withContext(Dispatchers.Main) {
            _orders.value = result
        }
    }

    fun downloadTicker(book: String) = viewModelScope.launch() {
        try {
            _loadingTicker.value = true
            val result = withContext(dispatcher) { currencyRepository.downloadTicker(book) }
            when (result) {
                is Resource.Success -> {
                    result.data?.let {
                        withContext(dispatcher) {
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
        } finally {
            _loadingTicker.value = false
        }
    }

    fun getTicker(book: String) = viewModelScope.launch(dispatcher) {
        val result = currencyRepository.getTicker(book)
        withContext(Dispatchers.Main) {
            _bookTicker.value = result
        }
    }

    fun getBookName(bookId: String) =  viewModelScope.launch() {
        val result = withContext(dispatcher) { currencyRepository.getCoinListBySymbol(bookId.getCurrencyCode()) }
        _coinName.value = result
    }
}
