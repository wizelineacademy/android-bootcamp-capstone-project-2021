package dev.ricsarabia.cryptochallenge.ui

import androidx.lifecycle.*
import dev.ricsarabia.cryptochallenge.data.repos.BitsoRepo
import dev.ricsarabia.cryptochallenge.domain.*
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val repo: BitsoRepo = BitsoRepo()
    val loading = MediatorLiveData<Boolean>()
    val errorMessage = MutableLiveData("")

    val books = MutableLiveData(listOf<Book>())
    val selectedBook = MutableLiveData("")
    val selectedBookPrices = MutableLiveData(BookPrices("", "", "", ""))
    val selectedBookOrders = MutableLiveData(BookOrders(listOf(), listOf()))
    // TODO: Create private variables in order to expose livedata correctly

    val gettingBooks = MutableLiveData(false)
    val gettingPrices = MutableLiveData(false)
    val gettingOrders = MutableLiveData(false)

    init {
        loading.addSource(gettingBooks) { loading.value = getLoadingStatus() }
        loading.addSource(gettingPrices) { loading.value = getLoadingStatus() }
        loading.addSource(gettingOrders) { loading.value = getLoadingStatus() }
    }

    fun getLoadingStatus(): Boolean {
        return gettingPrices.value == true || gettingBooks.value == true || gettingOrders.value == true
    }

    fun getBooks() {
        gettingBooks.value = true
        viewModelScope.launch {
            when (val availableBooks = repo.getBooks()) {
                is BooksData.Data -> books.value = availableBooks.books
                    .sortedWith(compareBy({ it.major }, { it.minor }))
                is BooksData.Error -> errorMessage.value = availableBooks.message
            }
            gettingBooks.value = false
        }
    }

    fun getBookPrices() {
        gettingPrices.value = true
        viewModelScope.launch {
            when (val prices = repo.getBookPrices(selectedBook.value!!)) {
                is BookPricesData.Data -> selectedBookPrices.value = prices.bookPrices
                is BookPricesData.Error -> errorMessage.value = prices.message
            }
            gettingPrices.value = false
        }
    }

    fun getBookOrders() {
        gettingOrders.value = true
        viewModelScope.launch {
            when (val orders = repo.getBookOrders(selectedBook.value!!)) {
                is BookOrdersData.Data -> selectedBookOrders.value = orders.bookOrders
                is BookOrdersData.Error -> errorMessage.value = orders.message
            }
            gettingOrders.value = false
        }
    }
}