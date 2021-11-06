package com.kcruz.cryptochallenge.usecase.data

import com.kcruz.cryptochallenge.commons.Response

//TODO: Add documentation

interface IBookRepository {

    suspend fun getAvailableBooks(): Response

    suspend fun getBookInfo(book: String): Response

    suspend fun getOpenOrders(book: String, aggregate: Boolean?): Response
}