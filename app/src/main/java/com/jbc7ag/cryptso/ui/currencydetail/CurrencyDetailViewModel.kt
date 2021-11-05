package com.jbc7ag.cryptso.ui.currencydetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jbc7ag.cryptso.data.model.BookDetail
import com.jbc7ag.cryptso.data.model.OrderDetail
import com.jbc7ag.cryptso.data.repository.CurrencyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrencyViewModel @Inject constructor(
    private val currencyRepository: CurrencyRepository
) : ViewModel() {

    private val _bookTicker= MutableLiveData<BookDetail>()
    val bookTicker: LiveData<BookDetail>
        get() = _bookTicker

    private val _orders= MutableLiveData<OrderDetail>()
    val orders: LiveData<OrderDetail>
        get() = _orders

    private val _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error


    fun getTicker(book: String)  = viewModelScope.launch {

        try {
            val result = currencyRepository.getTicker(book)

            result.let {
                if (it.isSuccessful) {
                    val successData = it.body()?.success
                    if(successData == true){
                        _bookTicker.postValue(it.body()?.payload)
                    }else{
                        _error.postValue("${it.body()?.error?.code}: ${it.body()?.error?.message}")
                    }
                } else {
                    _error.postValue(it.errorBody().toString())
                }
            }
        }catch (ex: Exception){
            _error.postValue(ex.localizedMessage)
        }
    }

    fun getOrders(book: String)  = viewModelScope.launch {

        try {
            val result = currencyRepository.getOrders(book)

            result.let {
                if (it.isSuccessful) {
                    val successData = it.body()?.success
                    if(successData == true){
                        _orders.postValue(it.body()?.payload)
                    }else{
                        _error.postValue("${it.body()?.error?.code}: ${it.body()?.error?.message}")
                    }
                } else {
                    _error.postValue(it.errorBody().toString())
                }
            }
        }catch (ex: Exception){
            _error.postValue(ex.localizedMessage)
        }
    }
}