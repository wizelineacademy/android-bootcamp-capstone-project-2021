package com.jbc7ag.cryptso.data.repository

import androidx.annotation.WorkerThread
import com.jbc7ag.cryptso.data.model.*
import com.jbc7ag.cryptso.data.room.dao.BooksDao
import com.jbc7ag.cryptso.data.room.dao.CoinListDao
import com.jbc7ag.cryptso.data.services.CurrencyApi
import com.jbc7ag.cryptso.util.Resource
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CurrencyRepository @Inject constructor(
    private val client: CurrencyApi,
    private val coinListDao: CoinListDao,
    private val BooksDao: BooksDao
) {
    suspend fun getAvailableBooks(): Response<AvailableBooks> = client.getAvailableBooks()
    suspend fun getTicker(book: String): Response<Ticker> = client.getTicker(book)
    suspend fun getOrders(book: String): Response<Orders> = client.getOrder(book)

    //coinList repository
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(coin: Coins) {
        coinListDao.insert(coin)
    }

    //Available Books Repository
    suspend fun downloadAvailableBooks(): Resource<List<Book>>{
            return try{
                val response = client.getAvailableBooks()
                val result = response.body()
                if(response.isSuccessful && result != null){
                    deleteBooks()
                    Resource.Success(result.payload)

                }else{
                    Resource.Error(result?.error?.message)
                }
            }catch(e: Exception){
                Resource.Error(e.message)
        }
    }

    fun saveBooks(bookList: List<Book>){
        BooksDao.insert(bookList)
    }

    fun getBooks(): List<Book>{
       return BooksDao.getBooks()
    }

    fun deleteBooks(){
        return BooksDao.delete()
    }


}
