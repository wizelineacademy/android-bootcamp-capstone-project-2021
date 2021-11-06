package com.alexbar10.cryptotrack.ui.cryptoAvailable

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexbar10.cryptotrack.domain.Cryptocurrency
import com.alexbar10.cryptotrack.domain.Failure
import com.alexbar10.cryptotrack.domain.Success
import com.alexbar10.cryptotrack.networking.repo.CryptocurrenciesRepo
import com.alexbar10.cryptotrack.utils.getMarketFor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CryptocurrenciesAvailableViewModel @Inject constructor(
    private val cryptocurrenciesRepo: CryptocurrenciesRepo
): ViewModel() {

    private val _loadingLiveData = MutableLiveData<Boolean>()
    val loadingLiveData: LiveData<Boolean> get() = _loadingLiveData

    private val _cryptoAvailableDetailsLiveData = MutableLiveData<List<Cryptocurrency>>()
    val cryptoAvailableDetailsLiveData: LiveData<List<Cryptocurrency>> get() = _cryptoAvailableDetailsLiveData

    private val _errorLiveData = MutableLiveData<Throwable?>()
    val errorLiveData: LiveData<Throwable?> get() = _errorLiveData

    private val _cryptoFilterLiveData = MutableLiveData<List<Cryptocurrency>>()
    val cryptoFilterLiveData: LiveData<List<Cryptocurrency>> get() = _cryptoFilterLiveData

    private val _marketsLiveData = MutableLiveData<MutableSet<String>>()
    val marketsLiveData: LiveData<MutableSet<String>> get() = _marketsLiveData

    fun getCryptocurrenciesAvailable() {
        _cryptoAvailableDetailsLiveData.postValue(emptyList())
        _loadingLiveData.postValue(true)

        viewModelScope.launch {
            val currenciesResponse = cryptocurrenciesRepo.getCryptocurrenciesAvailable()
            _loadingLiveData.postValue(false)

            if (currenciesResponse is Success) {
                _cryptoAvailableDetailsLiveData.postValue(currenciesResponse.data.payload)

                // Download the ticker to get the last price of each crypto
                currenciesResponse.data.payload?.forEach {
                    getTickerFor(it)
                }

                // Show all markets the first time
                _marketsLiveData.value = mutableSetOf("mxn","btc","ars", "brl", "dai", "usd")
            } else {
                val failure = currenciesResponse as Failure
                _errorLiveData.postValue(failure.error)
            }
        }
    }

    private fun getTickerFor(cryptocurrency: Cryptocurrency) {
        viewModelScope.launch {
            val tickerResponse = cryptocurrenciesRepo.getTicketFor(cryptocurrency.book)

            if (tickerResponse is Success) {
                cryptocurrency.ticker = tickerResponse.data.payload

                // Update list of cryptos with the new ticker
                val list = _cryptoAvailableDetailsLiveData.value?.toMutableList()
                list?.let {
                    var currencyIndex = list.indexOf(cryptocurrency)
                    list[currencyIndex] = cryptocurrency
                }
                _cryptoAvailableDetailsLiveData.postValue(list?.toList())

            } else {
                val failure = tickerResponse as Failure
                println("Error getting ticker: ${failure.error}")
            }
        }
    }

    // return the list of cryptos of the markets selected
    fun showCryptocurrenciesFor(market: String, isAdded: Boolean){
        var list = _marketsLiveData.value

        list?.let {
            if (isAdded) {
                list.add(market)
            } else {
                list.remove(market)
            }

            // Filter main list
            val listFiltered = _cryptoAvailableDetailsLiveData.value?.filter { currency -> getMarketFor(currency) in list }
            _cryptoFilterLiveData.postValue(listFiltered)
        }
    }
}