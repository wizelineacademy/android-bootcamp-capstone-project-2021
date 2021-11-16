package com.example.bootcampproject.data.repo

import com.example.bootcampproject.data.local.CurrencyDao
import com.example.bootcampproject.data.mock.StatusAvailableBooks
import com.example.bootcampproject.data.services.BitsoServices
import com.example.bootcampproject.domain.Currency
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton

private const val CURRENCY_BASE_IMAGE_URL = "https://cryptoicon-api.vercel.app/api/icon/"

@Singleton
class CurrencyRepo @Inject constructor(
    private val bitsoServices :BitsoServices,
    private val currencyDao: CurrencyDao
){

   suspend fun getCurrencies():List<Currency>{

       val _currencies = mutableListOf<Currency>()
       try {
           val call = bitsoServices.getAvailableBooks()
           if(call.isSuccessful){
               addCurrencies(call.body(),_currencies)
               currencyDao.insertAll(_currencies)
               return _currencies
           }
           return currencyDao.getAll()
       }catch (e:Exception){
           return currencyDao.getAll()
       }
       /* CoroutineScope(Dispatchers.IO).launch {
            val call = bitsoServices.getAvailableBooks()

            call.enqueue(object : Callback<StatusAvailableBooks> {
                override fun onResponse(
                    call: Call<StatusAvailableBooks>,
                    response: Response<StatusAvailableBooks>,
                ) {

                    response.body()?.let { availableBooks ->
                        addCurrencies(availableBooks,_currencies)
                        currencies.postValue(_currencies)
                    }
                }
                override fun onFailure(call: Call<StatusAvailableBooks>, t: Throwable) {
                    call.cancel()
                }

            })

        }*/
    }
    private fun addCurrencies(availableBooks:StatusAvailableBooks?, _currencies:MutableList<Currency>){
        if (availableBooks != null) {
            for(availableBook in availableBooks.payload){
                val splitNameBook= availableBook.book.split("_")
                val tempCurrency = Currency(
                    code = splitNameBook[0],
                    name = splitNameBook[0],
                    imageUrl = CURRENCY_BASE_IMAGE_URL+splitNameBook[0]
                )
                if(!_currencies.contains(tempCurrency)){
                    _currencies.add(tempCurrency)
                }
            }
        }
    }

}