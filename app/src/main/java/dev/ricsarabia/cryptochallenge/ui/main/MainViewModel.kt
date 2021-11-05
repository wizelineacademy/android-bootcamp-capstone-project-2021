package dev.ricsarabia.cryptochallenge.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.ricsarabia.cryptochallenge.data.repos.BitsoRepo
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    fun apiTest() {
        val repo = BitsoRepo()
        viewModelScope.launch {
            repo.getAvailableBooks()
            repo.getOrderBook("btc_mxn")
            repo.getTicker("btc_mxn")
        }
    }

}