package com.esaudev.wizeline.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.esaudev.wizeline.model.AvailableBook
import com.esaudev.wizeline.repository.BitsoRepository
import com.esaudev.wizeline.utils.Constants.NETWORK_UNKNOWN_ERROR
import com.esaudev.wizeline.utils.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val repository: BitsoRepository
) : ViewModel() {

    private var _getAvailableBooksEvent = MutableLiveData<DataState<List<AvailableBook>>>()
    val getAvailableBooksEvent: LiveData<DataState<List<AvailableBook>>> = _getAvailableBooksEvent

    fun getAvailableBooks() {
        viewModelScope.launch {
            try {
                _getAvailableBooksEvent.value = DataState.Loading
                val data = repository.getAvailableBooks()
                _getAvailableBooksEvent.value = data
            } catch (e: Exception) {
                _getAvailableBooksEvent.value = DataState.Error(NETWORK_UNKNOWN_ERROR)
            }
        }
    }
}
