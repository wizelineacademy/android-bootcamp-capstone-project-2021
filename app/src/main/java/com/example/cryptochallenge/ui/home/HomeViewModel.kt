package com.example.cryptochallenge.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.cryptochallenge.data.repository.CryptoRepository
import com.example.cryptochallenge.domain.availablebook.Payload
import com.example.cryptochallenge.ui.commons.SingleLiveEvent
import com.example.cryptochallenge.usecases.GetAvailableBooks

/**
 * ViewModel for home fragment
 */
class HomeViewModel : ViewModel() {
    /**
     * Property to handle [CryptoRepository]
     */
    private val cryptoRepository = CryptoRepository()

    /**
     * LiveData for event trigger
     */
    private val _eventTrigger = SingleLiveEvent<HomeEvent>()
    val eventTrigger: LiveData<HomeEvent> get() = _eventTrigger

    /**
     * Get available books list
     *
     * @return [LiveData] with web service response
     */
    fun getAvailableBooks(): LiveData<List<Payload>?> {
        return GetAvailableBooks(cryptoRepository).execute()
    }

    /**
     * Trigger event that show available book detail
     *
     * @param cryptoName Available book name
     */
    fun showCryptoDetail(cryptoName: String) {
        if (cryptoName.isNotEmpty())
            _eventTrigger.value = HomeEvent.OnShowCryptoDetail(cryptoName)
    }

    /**
     * Clean disposable property
     */
    fun clear() {
        cryptoRepository.clear()
    }
}