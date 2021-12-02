package dev.ricsarabia.cryptochallenge.ui.detail

import android.app.Application
import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.ricsarabia.cryptochallenge.core.CryptoChallengeApp
import dev.ricsarabia.cryptochallenge.data.repos.BitsoRepo
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val repo: BitsoRepo) : ViewModel() {
    private val _loading = MediatorLiveData<Boolean>()
    private val selectedBook = MutableLiveData("")
    private val gettingPrices = MutableLiveData(false)
    private val gettingOrders = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading
    val selectedBookPrices = Transformations.switchMap(selectedBook) { repo.bookPricesOf(it).asLiveData() }
    val selectedBookAsks = Transformations.switchMap(selectedBook) { repo.asksOf(it).asLiveData() }
    val selectedBookBids = Transformations.switchMap(selectedBook) { repo.bidsOf(it).asLiveData() }
    // TODO: Display advice when theres no data available

    init {
        _loading.addSource(gettingPrices) { _loading.value = getLoadingStatus() }
        _loading.addSource(gettingOrders) { _loading.value = getLoadingStatus() }
    }

    private fun getLoadingStatus(): Boolean {
        return gettingPrices.value == true || gettingOrders.value == true
    }

    fun setSelectedBook(book: String) {
        selectedBook.value = book
    }

    fun updateBookPrices() {
        gettingPrices.value = true
        viewModelScope.launch {
            selectedBook.value?.let { repo.updateBookPrices(it)}
            gettingPrices.value = false
        }
    }

    fun updateBookOrders() {
        gettingOrders.value = true
        viewModelScope.launch {
            selectedBook.value?.let { repo.updateBookOrders(it) }
            gettingOrders.value = false
        }
    }
}