package com.kcruz.cryptochallenge.data.source.remote

import com.kcruz.cryptochallenge.commons.Response

interface IBookRemoteSource {
    suspend fun getAvailableBooks(): Response

    suspend fun getBookInfo(book: String): Response

    suspend fun getOpenOrders(book: String, aggregate: Boolean? = null): Response
}