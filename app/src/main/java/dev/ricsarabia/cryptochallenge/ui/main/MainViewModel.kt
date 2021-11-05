package dev.ricsarabia.cryptochallenge.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.ricsarabia.cryptochallenge.data.repos.BitsoRepo
import dev.ricsarabia.cryptochallenge.domain.AvailableBooks
import dev.ricsarabia.cryptochallenge.domain.Book
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val repo: BitsoRepo = BitsoRepo()
    val loading = MutableLiveData(false)
    val books = MutableLiveData(listOf<Book>())
    val errorMessage = MutableLiveData("")

    fun apiTest() {
        viewModelScope.launch {
            repo.getAvailableBooks()
            repo.getOrderBook("btc_mxn")
            repo.getTicker("btc_mxn")
        }
    }

    fun getAvailableBooks(){
        loading.value = true
        viewModelScope.launch {
            when (val availableBooks = repo.getAvailableBooks()){
                is AvailableBooks.Books -> books.value = availableBooks.books
                is AvailableBooks.Error -> {errorMessage.value = availableBooks.message}
            }
            loading.value = false
        }
    }

}