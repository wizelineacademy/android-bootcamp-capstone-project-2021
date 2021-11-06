package com.alexbar10.cryptotrack.ui.cryptoDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexbar10.cryptotrack.domain.Cryptocurrency
import com.alexbar10.cryptotrack.domain.Failure
import com.alexbar10.cryptotrack.domain.OrderResponse
import com.alexbar10.cryptotrack.domain.Success
import com.alexbar10.cryptotrack.networking.repo.CryptocurrenciesRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CryptocurrencyDetailsViewModel @Inject constructor(
    private val cryptocurrenciesRepo: CryptocurrenciesRepo
): ViewModel() {

    private val _loadingLiveData = MutableLiveData<Boolean>()
    val loadingLiveData: LiveData<Boolean> get() = _loadingLiveData

    private val _errorLiveData = MutableLiveData<Throwable?>()
    val errorLiveData: LiveData<Throwable?> get() = _errorLiveData

    private val _cryptocurrencyOrderLiveData = MutableLiveData<OrderResponse>()
    val cryptocurrencyOrderLiveData: LiveData<OrderResponse> get() = _cryptocurrencyOrderLiveData

    fun getOrderFor(cryptocurrency: Cryptocurrency) {
        _loadingLiveData.postValue(true)

        viewModelScope.launch {
            val orderResponse = cryptocurrenciesRepo.getOrderFor(cryptocurrency.book)
            _loadingLiveData.postValue(false)

            if (orderResponse is Success) {
                _cryptocurrencyOrderLiveData.postValue(orderResponse.data)
            } else {
                val failure = orderResponse as Failure
                _errorLiveData.postValue(failure.error)
            }
        }
    }
}