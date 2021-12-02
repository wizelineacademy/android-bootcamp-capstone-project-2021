package dev.ricsarabia.cryptochallenge.ui.main

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.ricsarabia.cryptochallenge.data.repos.BitsoRepo
import javax.inject.Inject
import kotlinx.coroutines.launch

/**
 * Created by Ricardo Sarabia on 2021/11/22.
 */
@HiltViewModel
class MainViewModel @Inject constructor(private val repo: BitsoRepo) : ViewModel() {

    private val _gettingBooks = MutableLiveData(false)
    val gettingBooks: LiveData<Boolean> = _gettingBooks
    val books = repo.books().asLiveData()
    // TODO: Display advice when theres no data available

    fun updateBooks() {
        _gettingBooks.value = true
        viewModelScope.launch {
            repo.updateBooks()
            _gettingBooks.value = false
        }
    }
}
