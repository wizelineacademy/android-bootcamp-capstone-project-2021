package com.example.cryptochallenge.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptochallenge.common.toBook
import com.example.cryptochallenge.data.local.BookDAO
import com.example.cryptochallenge.data.model.GetBookEvent
import com.example.cryptochallenge.data.model.GetBookFailure
import com.example.cryptochallenge.data.model.GetBookSuccess
import com.example.cryptochallenge.di.IOThread
import com.example.cryptochallenge.di.MainThread
import com.example.cryptochallenge.domain.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: BookRepository,
    private val compositeDisposable: CompositeDisposable,
    @IOThread private val schedulers: Scheduler,
    @MainThread private val mainThread: Scheduler,
    private val localBookSource: BookDAO,
    ): ViewModel(){
    private var _getBookEvent = MutableLiveData<GetBookEvent>()
    val getBookEvent: LiveData<GetBookEvent> = _getBookEvent

    fun getBooks() {
        viewModelScope.launch {
            try {
                val data = repository.getBooks()
                _getBookEvent.value = GetBookSuccess(data)
            } catch (e: Exception) {
                _getBookEvent.value = GetBookFailure(e)
            }
        }
    }

    fun getBooksWithRx() {
        try {
            compositeDisposable.add(
                repository.getBooksWithRx()
                    .subscribeOn(schedulers)
                    .observeOn(schedulers)
                    .subscribe{
                        it.payload.map { it.toBook() }.onEach {
                            localBookSource.insertBook(it)
                        }
                    }
            )
            compositeDisposable.add(
                repository.getBooksWithRx()
                    .subscribeOn(schedulers)
                    .observeOn(mainThread)
                    .subscribe{ response ->
                        var booksList = response.payload.map { it.toBook() }
                        _getBookEvent.value = GetBookSuccess(booksList)
                    }
            )
        } catch (e: Exception) {
            _getBookEvent.value = GetBookFailure(e)
        }
    }
}