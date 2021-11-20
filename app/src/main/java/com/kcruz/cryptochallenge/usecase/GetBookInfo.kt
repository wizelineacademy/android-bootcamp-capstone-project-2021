package com.kcruz.cryptochallenge.usecase

import com.kcruz.cryptochallenge.commons.Response
import com.kcruz.cryptochallenge.usecase.data.IBookRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

//TODO: Add documentation

class GetBookInfo(private val bookRepository: IBookRepository) {

    suspend fun getBookInfo(book: String): Response {
        return withContext(Dispatchers.IO) {
            bookRepository.getBookInfo(book)
        }
    }
}