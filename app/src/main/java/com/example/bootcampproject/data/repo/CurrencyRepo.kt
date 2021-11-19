package com.example.bootcampproject.data.repo

import android.util.Log
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
    private val bitsoServices: BitsoServices,
    private val currencyDao: CurrencyDao
) {

    suspend fun getCurrencies(isConnected: Boolean): List<Currency> {
        if (isConnected) {
            try {
                val call = bitsoServices.getAvailableBooks()
                val _currencies = addCurrencies(call.body())
                currencyDao.insertAll(_currencies)
                return _currencies
            } catch (e: Exception) {
                return currencyDao.getAll()
            }
        }
        return currencyDao.getAll()
        /* CoroutineScope(Dispatchers.IO).launch {
        Log.d("TAG2", "message22222222222")
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

    private fun addCurrencies(availableBooks: StatusAvailableBooks?): List<Currency> {
        val _currencies = mutableListOf<Currency>()
        availableBooks?.payload?.iterator()?.forEach { availableBook ->
            val splitNameBook = availableBook.book.split("_")
            val tempCurrency = Currency(
                code = splitNameBook[0],
                name = splitNameBook[0],
                imageUrl = CURRENCY_BASE_IMAGE_URL + splitNameBook[0]
            )
            if (!_currencies.contains(tempCurrency)) {
                _currencies.add(tempCurrency)
            }
        }
        return _currencies
    }

}