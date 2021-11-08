package com.example.capstoneproject.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.capstoneproject.model.api.BitsoResponse
import com.example.capstoneproject.model.api.Currency
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


    var currencyList = MutableLiveData<List<Currency>> ()

    fun loadCurrencies()
    {
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
                        currencyList.postValue(list)
                    }
                }

                override fun onFailure(call: Call<BitsoResponse>, t: Throwable) {
                    Log.e("BITSO", "onFailure")
                    call.cancel()
                }
            })
        }

    }
}