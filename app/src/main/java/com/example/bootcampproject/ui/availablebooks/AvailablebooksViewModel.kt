package com.example.bootcampproject.ui.availablebooks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bootcampproject.data.mock.AvailableBook
import com.example.bootcampproject.data.repo.AvailableBooksRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AvailablebooksViewModel @Inject constructor(
    private val currencyRepo: AvailableBooksRepo
) : ViewModel(){
    private val _books: MutableLiveData<List<AvailableBook>> = MutableLiveData()
    val books: LiveData<List<AvailableBook>> = _books

    fun getAvailableBooks(code:String?,isConnected:Boolean){
        CoroutineScope(Dispatchers.IO).launch {
            _books.postValue( currencyRepo.getAvailableBooks(code,isConnected))
        }
    }
}