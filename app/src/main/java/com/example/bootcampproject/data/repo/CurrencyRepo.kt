package com.example.bootcampproject.data.repo

import androidx.lifecycle.MutableLiveData
import com.example.bootcampproject.data.mock.AvailableBook
import com.example.bootcampproject.data.services.BitsoServices
import com.example.bootcampproject.domain.Currency
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

private const val CURRENCY_BASE_IMAGE_URL = "https://cryptoicon-api.vercel.app/api/icon/"

@Singleton
class CurrencyRepo @Inject constructor(
    private val bitsoServices :BitsoServices
){
    val _currencies = mutableListOf<Currency>()

    fun getCurrencies(currencies: MutableLiveData<List<Currency>>){
        CoroutineScope(Dispatchers.IO).launch {
            val call = bitsoServices.getAvailableBooks()

            call.enqueue(object : Callback<AvailableBook> {
                override fun onResponse(
                    call: Call<AvailableBook>,
                    response: Response<AvailableBook>,
                ) {

                    response.body()?.let { availableBooks ->
                        for(availableBook in availableBooks.payload){
                            val splitNameBook= availableBook.book.split("_")
                            val tempCurrency = Currency(
                                code = splitNameBook[0],
                                name = splitNameBook[0],
                                imageUrl = CURRENCY_BASE_IMAGE_URL+splitNameBook[0]
                            )
                            if(!_currencies.contains(tempCurrency))
                                _currencies.add(tempCurrency)
                        }
                        currencies.postValue(_currencies)
                    }
                }

                override fun onFailure(call: Call<AvailableBook>, t: Throwable) {
                    call.cancel()
                }

            })

        }
    }
}