package com.jbc7ag.cryptso.ui.currencieslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jbc7ag.cryptso.data.model.Book
import com.jbc7ag.cryptso.data.repository.CurrencyRepository
import com.jbc7ag.cryptso.util.getBooksError
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrenciesViewModel @Inject constructor(
    private val currencyRepository: CurrencyRepository
) : ViewModel() {

    private val _availableBooks = MutableLiveData<List<Book>>()
    val availableBooks: LiveData<List<Book>>
        get() = _availableBooks

    private val _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error


    fun getAvailableBooks() = viewModelScope.launch {

        try {
            val result = currencyRepository.getAvailableBooks()

            result.let {
                if (it.isSuccessful) {
                    val successData = it.body()?.success
                    if (successData == true) {
                        _availableBooks.value = it.body()?.payload
                    } else {
                        _error.value = it.getBooksError()
                    }
                } else {
                    _error.value = it.errorBody().toString()
                }
            }
        } catch (ex: Exception) {
            _error.value = ex.localizedMessage
        }
    }
}