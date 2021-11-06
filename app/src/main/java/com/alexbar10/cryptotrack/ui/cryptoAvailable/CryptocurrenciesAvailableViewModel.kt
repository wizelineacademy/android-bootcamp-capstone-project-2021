package com.alexbar10.cryptotrack.ui.cryptoAvailable

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexbar10.cryptotrack.domain.Cryptocurrency
import com.alexbar10.cryptotrack.domain.Failure
import com.alexbar10.cryptotrack.domain.Success
import com.alexbar10.cryptotrack.networking.repo.CryptocurrenciesRepo
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

    fun getCryptocurrenciesAvailable() {
        _loadingLiveData.postValue(true)

        viewModelScope.launch {
            val currenciesResponse = cryptocurrenciesRepo.getCryptocurrenciesAvailable()
            _loadingLiveData.postValue(false)

            if (currenciesResponse is Success) {
                _cryptoAvailableDetailsLiveData.postValue(currenciesResponse.data.payload)
            } else {
                val failure = currenciesResponse as Failure
                _errorLiveData.postValue(failure.error)
            }
        }
    }
}