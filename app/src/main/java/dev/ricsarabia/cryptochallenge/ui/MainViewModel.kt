package dev.ricsarabia.cryptochallenge.ui

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import dev.ricsarabia.cryptochallenge.core.CryptoChallengeApp
import kotlinx.coroutines.launch

class MainViewModel(app: Application) : AndroidViewModel(app) {
    private val repo = (app as CryptoChallengeApp).repository

    // TODO: Create private variables in order to expose livedata correctly
    val loading = MediatorLiveData<Boolean>()
    val errorMessage = MutableLiveData("")
    val selectedBook = MutableLiveData("")
    val books = repo.books.asLiveData()
    val selectedBookPrices = Transformations.switchMap(selectedBook) { repo.bookPricesOf(it).asLiveData() }
    val selectedBookAsks = Transformations.switchMap(selectedBook) { repo.asksOf(it).asLiveData() }
    val selectedBookBids = Transformations.switchMap(selectedBook) { repo.bidsOf(it).asLiveData() }

    private val gettingBooks = MutableLiveData(false)
    private val gettingPrices = MutableLiveData(false)
    private val gettingOrders = MutableLiveData(false)

    init {
        loading.addSource(gettingBooks) { loading.value = getLoadingStatus() }
        loading.addSource(gettingPrices) { loading.value = getLoadingStatus() }
        loading.addSource(gettingOrders) { loading.value = getLoadingStatus() }
    }

    private fun getLoadingStatus(): Boolean {
        return gettingPrices.value == true || gettingBooks.value == true || gettingOrders.value == true
    }

    fun updateBooks() {
        gettingBooks.value = true
        viewModelScope.launch {
            when (repo.updateBooks()) {
                true -> Log.wtf("updateBooks", "UPDATED") // TODO: Show "updated_at" on UI
                false -> Log.wtf("updateBooks", "ERROR")
            }
            gettingBooks.value = false
        }
    }

    fun updateBookPrices() {
        gettingPrices.value = true
        viewModelScope.launch {
            when (repo.updateBookPrices(selectedBook.value!!)) {
                true -> Log.wtf("updateBookPrices", "UPDATED") // TODO: Show "updated_at" on UI
                false -> Log.wtf("updateBookPrices", "ERROR")
            }
            gettingPrices.value = false
        }
    }

    fun updateBookOrders() {
        gettingOrders.value = true
        viewModelScope.launch {
            when (repo.updateBookOrders(selectedBook.value!!)) {
                true -> Log.wtf("updateBookOrders", "UPDATED") // TODO: Show "updated_at" on UI
                false -> Log.wtf("updateBookOrders", "ERROR")
            }
            gettingOrders.value = false
        }
    }
}