package com.example.bootcampproject.data.repo

import com.example.bootcampproject.data.local.CurrencyDao
import com.example.bootcampproject.data.services.BitsoServices
import com.example.bootcampproject.domain.Currency
import com.example.bootcampproject.util.getCurrencies
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CurrencyRepo @Inject constructor(
    private val bitsoServices: BitsoServices,
    private val currencyDao: CurrencyDao

) {

    suspend fun getCurrencies(isConnected: Boolean): List<Currency> {
        if (isConnected) {
            return try {
                val call = bitsoServices.getAvailableBooks()
                val _currencies = call.body().getCurrencies()
                // currencyDao.insertAll(_currencies)
                _currencies
            } catch (e: Exception) {
                currencyDao.getAll()
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
}
