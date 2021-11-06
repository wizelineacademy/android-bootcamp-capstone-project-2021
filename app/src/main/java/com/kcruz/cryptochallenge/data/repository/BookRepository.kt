package com.kcruz.cryptochallenge.data.repository

import com.kcruz.cryptochallenge.data.source.remote.IBookRemoteSource

class BookRepository(private val remoteSource: IBookRemoteSource) {

    suspend fun getAvailableBooks() = remoteSource.getAvailableBooks()

    suspend fun getBookInfo(book: String) = remoteSource.getBookInfo(book)

    suspend fun getOpenOrders(book: String, aggregate: Boolean?) = remoteSource.getOpenOrders(book, aggregate)

}