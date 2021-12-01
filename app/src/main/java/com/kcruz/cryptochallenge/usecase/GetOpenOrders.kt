package com.kcruz.cryptochallenge.usecase

import com.kcruz.cryptochallenge.commons.Response
import com.kcruz.cryptochallenge.usecase.data.IBookRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

//TODO: Add documentation

class GetOpenOrders(private val bookRepository: IBookRepository) {

    suspend fun getOpenOrders(book: String, aggregate: Boolean?): Response {
        return withContext(Dispatchers.IO) {
            bookRepository.getOpenOrders(book, aggregate)
        }
    }
}