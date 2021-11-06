package dev.ricsarabia.cryptochallenge.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.ricsarabia.cryptochallenge.data.repos.BitsoRepo
import dev.ricsarabia.cryptochallenge.domain.*
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val repo: BitsoRepo = BitsoRepo()
    val loading = MutableLiveData(false)
    val errorMessage = MutableLiveData("")

    val books = MutableLiveData(listOf<Book>())
    val selectedBook = MutableLiveData("")
    val selectedBookPrices = MutableLiveData(BookPrices("","","",""))
    val selectedBookOrders = MutableLiveData(BookOrders(listOf(),listOf()))

    fun getBooks() {
        loading.value = true
        viewModelScope.launch {
            when (val availableBooks = repo.getBooks()){
                is BooksData.Data -> books.value = availableBooks.books
                is BooksData.Error -> {errorMessage.value = availableBooks.message}
            }
            loading.value = false
        }
    }

    fun getBookPrices() {
        viewModelScope.launch {
            when (val prices = repo.getBookPrices(selectedBook.value!!)){
                is BookPricesData.Data -> selectedBookPrices.value = prices.bookPrices
                is BookPricesData.Error -> errorMessage.value = prices.message
            }
        }
    }

    fun getBookOrders() {
        viewModelScope.launch {
            when (val orders = repo.getBookOrders(selectedBook.value!!)){
                is BookOrdersData.Data -> selectedBookOrders.value = orders.bookOrders
                is BookOrdersData.Error -> errorMessage.value = orders.message
            }
        }
    }
}