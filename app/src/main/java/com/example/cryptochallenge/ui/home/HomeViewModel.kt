package com.example.cryptochallenge.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cryptochallenge.data.repository.CryptoRepository
import com.example.cryptochallenge.domain.availablebook.Payload
import com.example.cryptochallenge.ui.commons.SingleLiveEvent
import com.example.cryptochallenge.usecases.GetAvailableBooks

class HomeViewModel : ViewModel() {
    private val cryptoRepository = CryptoRepository()

    private val _eventTrigger = SingleLiveEvent<HomeEvent>()
    val eventTrigger: LiveData<HomeEvent> get() = _eventTrigger

    fun getAvailableBooks(): LiveData<List<Payload>?> {
        return GetAvailableBooks(cryptoRepository).execute()
    }

    fun showCryptoDetail(cryptoName: String) {
        if (cryptoName.isNotEmpty())
            _eventTrigger.value = HomeEvent.OnShowCryptoDetail(cryptoName)
    }

    fun clear() {
        cryptoRepository.clear()
    }
}