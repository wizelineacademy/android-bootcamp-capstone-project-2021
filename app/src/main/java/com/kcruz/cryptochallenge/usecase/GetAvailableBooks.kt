package com.kcruz.cryptochallenge.usecase

import com.kcruz.cryptochallenge.commons.Response
import com.kcruz.cryptochallenge.domain.ExchangeOrderBook
import com.kcruz.cryptochallenge.usecase.data.IBookRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

//TODO: Add documentation

class GetAvailableBooks(private val bookRepository: IBookRepository) {

    suspend fun getAvailableBooks(): Response {
        return withContext(Dispatchers.IO) {
            bookRepository.getAvailableBooks()
        }
    }
}