package com.example.bootcampproject.data.repo


import com.example.bootcampproject.data.local.AvailableBooksDao
import com.example.bootcampproject.data.local.CurrencyDao
import com.example.bootcampproject.data.mock.AvailableBook
import com.example.bootcampproject.data.mock.StatusAvailableBooks
import com.example.bootcampproject.data.services.BitsoServices
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class AvailableBooksRepo@Inject constructor(
    private val bitsoServices : BitsoServices,
    private val availableBooksDao: AvailableBooksDao

) {
    suspend fun getAvailableBooks(code:String?):List<AvailableBook>{
        val _payloads = mutableListOf<AvailableBook>()
        try {
            val call = bitsoServices.getAvailableBooks()
            if(call.isSuccessful){
                addPayloads(code,call.body(),_payloads)
                availableBooksDao.insertAll(_payloads)
                return _payloads
            }
            return availableBooksDao.getSelectedBooks(code)
        }catch (e:Exception){
            return availableBooksDao.getSelectedBooks(code)
        }
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
   private fun addPayloads(code:String?,availableBooks: StatusAvailableBooks?,_payloads:MutableList<AvailableBook>){
        if (availableBooks != null) {
            for(availableBook in availableBooks.payload) {
                val splitNameBook = availableBook.book.split("_")
                if(code == splitNameBook[0]){
                    _payloads.add(availableBook)
                }
            }
        }
    }
}
