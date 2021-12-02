package com.example.cryptochallenge.data.remote

import com.example.cryptochallenge.data.model.GetBooks
import com.example.cryptochallenge.data.model.GetBookDetail
import com.example.cryptochallenge.data.model.GetBookOrdersDetail
import com.example.cryptochallenge.data.service.ApiService
import com.example.cryptochallenge.di.ApiServiceWithCoroutines
import com.example.cryptochallenge.di.ApiServiceWithRx
import io.reactivex.Observable
import javax.inject.Inject

class BookDataSource @Inject constructor(
    @ApiServiceWithCoroutines private val apiService: ApiService,
    @ApiServiceWithRx private val apiServiceWithRx: ApiService
) {
    fun getBooksWithRx(): Observable<GetBooks> = apiServiceWithRx.getBooksWithRx()
    suspend fun getBooks(): GetBooks = apiService.getBooks()
    suspend fun getBookDetails(book: String): GetBookDetail = apiService.getBookDetail(book)
    suspend fun getBookOrders(book: String): GetBookOrdersDetail = apiService.getBookOrders(book)
}