package com.jbc7ag.cryptso.ui.currencieslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jbc7ag.cryptso.data.model.Book
import com.jbc7ag.cryptso.data.model.Coins
import com.jbc7ag.cryptso.data.repository.CurrencyRepository
import com.jbc7ag.cryptso.util.Resource
import com.jbc7ag.cryptso.util.getBooksError
import dagger.hilt.android.lifecycle.HiltViewModel
import drewcarlson.coingecko.CoinGeckoClient
import drewcarlson.coingecko.models.coins.CoinList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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

    private var _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading

    private var _coinList = MutableLiveData<List<CoinList>>()

    init {
        getCoinList()
        downloadBooks()
    }

    //Using coinGecko library to get a friendly name of the Currency
    private fun getCoinList() = viewModelScope.launch {
        val coinGecko = CoinGeckoClient.create()
        _coinList.value = coinGecko.getCoinList()
    }

    private suspend fun insertCoins(listBooks: List<Book>) {
        listBooks.map { item ->
            val data = _coinList.value?.filter {
                it.symbol == item.book.split("_")[0] && !it.id.contains(
                    "binance",
                    ignoreCase = true
                )
            }?.get(0)
            data?.let {
                currencyRepository.insert(
                    Coins(
                        name = data.name,
                        symbol = data.symbol,
                        id = data.id
                    )
                )
            }
        }
    }

    private fun downloadBooks() = viewModelScope.launch() {
        try {
            _loading.value = true
            val result = withContext(Dispatchers.IO) { currencyRepository.downloadAvailableBooks() }
            when (result) {
                is Resource.Success -> {
                    result.data?.let {
                        withContext(Dispatchers.IO) {
                            insertCoins(it)
                            currencyRepository.saveBooks(it)
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
        _loading.value = false
    }

    fun getBooks() = viewModelScope.launch(Dispatchers.IO) {
        val result = currencyRepository.getBooks()
        withContext(Dispatchers.Main) {
            _availableBooks.value = result
        }
    }

}