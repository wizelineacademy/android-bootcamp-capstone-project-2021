package com.example.bootcampproject.data.repo

import com.example.bootcampproject.data.local.AvailableBooksDao
import com.example.bootcampproject.data.mock.AvailableBook
import com.example.bootcampproject.data.services.BitsoServices
import com.example.bootcampproject.util.filterData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AvailableBooksRepo @Inject constructor(
    private val bitsoServices: BitsoServices,
    private val availableBooksDao: AvailableBooksDao,
) {
    suspend fun getAvailableBooks(code: String?, isConnected: Boolean): List<AvailableBook> {

        if (isConnected) {
            try {
                val call = bitsoServices.getAvailableBooks()
                val _payloads = call.body()?.filterData(code)
                if (_payloads != null) {
                    return _payloads
                }
            } catch (e: Exception) {
                return availableBooksDao.getSelectedBooks(code)
            }
        }
        return availableBooksDao.getSelectedBooks(code)
/*        CoroutineScope(Dispatchers.IO).launch {
            val call =bitsoServices.getAvailableBooks()
            call.enqueue(object :Callback<StatusAvailableBooks> {
                override fun onResponse(
                    call: Call<StatusAvailableBooks>,
                    response: Response<StatusAvailableBooks>,
                ){
                    response.body()?.let { availableBooks ->
                        addPayloads(code,availableBooks,_payloads)
                        books.postValue(_payloads)
                    }
                }
                override fun onFailure(call: Call<StatusAvailableBooks>, t: Throwable) {
                    call.cancel()
                }
            })
        }*/
    }
}
