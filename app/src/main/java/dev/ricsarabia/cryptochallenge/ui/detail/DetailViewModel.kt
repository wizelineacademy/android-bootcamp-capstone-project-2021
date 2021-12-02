package dev.ricsarabia.cryptochallenge.ui.detail

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.ricsarabia.cryptochallenge.data.repos.BitsoRepo
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class DetailViewModel @Inject constructor(private val repo: BitsoRepo) : ViewModel() {
    private val _loading = MediatorLiveData<Boolean>()
    private val _refreshSucceeded = MutableLiveData(true)
    private val selectedBook = MutableLiveData("")
    private val gettingPrices = MutableLiveData(false)
    private val gettingOrders = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading
    val refreshSucceeded = Transformations.distinctUntilChanged(_refreshSucceeded)
    val selectedBookPrices =
        Transformations.switchMap(selectedBook) { repo.bookPricesOf(it).asLiveData() }
    val selectedBookAsks = Transformations.switchMap(selectedBook) { repo.asksOf(it).asLiveData() }
    val selectedBookBids = Transformations.switchMap(selectedBook) { repo.bidsOf(it).asLiveData() }
    val selectedBookLastRefresh =
        Transformations.switchMap(selectedBook) { repo.refreshTimeOf(it).asLiveData() }
    private val localDataUnavailable = Transformations.map(selectedBookPrices) { it == null }
    private val _dataError = MediatorLiveData<Boolean>()
    val dataError = Transformations.distinctUntilChanged(_dataError)

    init {
        _loading.addSource(gettingPrices) { _loading.value = getLoadingStatus() }
        _loading.addSource(gettingOrders) { _loading.value = getLoadingStatus() }
        _dataError.addSource(refreshSucceeded) { _dataError.value = getDataErrorStatus() }
        _dataError.addSource(localDataUnavailable) { _dataError.value = getDataErrorStatus() }
    }

    private fun getLoadingStatus(): Boolean {
        return gettingPrices.value == true || gettingOrders.value == true
    }

    private fun getDataErrorStatus(): Boolean {
        return refreshSucceeded.value == false && localDataUnavailable.value == true
    }

    fun setSelectedBook(book: String) {
        selectedBook.value = book
    }

    fun updateBookPrices() {
        gettingPrices.value = true
        viewModelScope.launch {
            selectedBook.value?.let { repo.updateBookPrices(it) }
            gettingPrices.value = false
        }
    }

    fun updateBookOrders() {
        gettingOrders.value = true
        viewModelScope.launch {
            selectedBook.value?.let { _refreshSucceeded.value = repo.updateBookOrders(it) }
            gettingOrders.value = false
        }
    }
}
