package dev.ricsarabia.cryptochallenge.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.ricsarabia.cryptochallenge.data.repos.BitsoRepo
import dev.ricsarabia.cryptochallenge.domain.BooksData
import dev.ricsarabia.cryptochallenge.domain.Book
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val repo: BitsoRepo = BitsoRepo()
    val loading = MutableLiveData(false)
    val books = MutableLiveData(listOf<Book>())
    val selectedCurrency = MutableLiveData("mxn")
    val errorMessage = MutableLiveData("")

    fun getBooks(){
        loading.value = true
        viewModelScope.launch {
            when (val availableBooks = repo.getBooks()){
                is BooksData.Data -> books.value = availableBooks.books
                is BooksData.Error -> {errorMessage.value = availableBooks.message}
            }
            loading.value = false
        }
    }
}