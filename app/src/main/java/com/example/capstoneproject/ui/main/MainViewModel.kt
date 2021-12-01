package com.example.capstoneproject.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.capstoneproject.model.api.BitsoResponse
import com.example.capstoneproject.model.api.BitsoTickerResponse
import com.example.capstoneproject.model.api.Currency
import com.example.capstoneproject.model.api.Ticker
import com.example.capstoneproject.service.CurrencyService
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainViewModel : ViewModel() {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.bitso.com/v3/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service: CurrencyService = retrofit.create(CurrencyService::class.java)

    private var _currencyList = MutableLiveData<List<Currency>>()
    val currencyList: LiveData<List<Currency>> get() = _currencyList

    private var _tickerInfo = MutableLiveData<Ticker>()
    val tickerInfo: LiveData<Ticker> get() = _tickerInfo

    fun loadCurrencies() {
        viewModelScope.launch {
            val currenciesCall = service.getCurrencyList()
            currenciesCall.enqueue(object : Callback<BitsoResponse> {
                override fun onResponse(
                    call: Call<BitsoResponse>,
                    response: Response<BitsoResponse>
                ) {
                    Log.e("BITSO", "onResponse " + response.isSuccessful)
                    Log.e("BITSO", "onResponse " + response.body()?.success.toString())
                    response.body()?.payload?.let { list ->
                        _currencyList.postValue(list)
                    }
                }

                override fun onFailure(call: Call<BitsoResponse>, t: Throwable) {
                    Log.e("BITSO", "onFailure")
                    call.cancel()
                }
            })
        }

    }

    fun getBookInfo(bookNameIn: String) {
        viewModelScope.launch {
            val currenciesCall = service.getCurrencyInfo(bookName = bookNameIn)
            currenciesCall.enqueue(object : Callback<BitsoTickerResponse> {
                override fun onResponse(
                    call: Call<BitsoTickerResponse>,
                    response: Response<BitsoTickerResponse>
                ) {
                    response.body()?.payload?.let { ticker ->
                        _tickerInfo.postValue(ticker)
                    }
                }

                override fun onFailure(call: Call<BitsoTickerResponse>, t: Throwable) {
                    Log.e("BITSO", "onFailure")
                    call.cancel()
                }
            })
        }

    }
}