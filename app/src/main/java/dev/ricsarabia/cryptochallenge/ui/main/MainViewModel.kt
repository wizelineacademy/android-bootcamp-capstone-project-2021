package dev.ricsarabia.cryptochallenge.ui.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import dev.ricsarabia.cryptochallenge.core.CryptoChallengeApp
import kotlinx.coroutines.launch

/**
 * Created by Ricardo Sarabia on 2021/11/22.
 */
class MainViewModel(app: Application) : AndroidViewModel(app) {
    private val repo = (app as CryptoChallengeApp).repository
    private val _gettingBooks = MutableLiveData(false)
    val gettingBooks: LiveData<Boolean> = _gettingBooks
    val books = repo.books.asLiveData()
    // TODO: Display advice when theres no data available

    fun updateBooks() {
        _gettingBooks.value = true
        viewModelScope.launch {
            repo.updateBooks()
            _gettingBooks.value = false
        }
    }
}