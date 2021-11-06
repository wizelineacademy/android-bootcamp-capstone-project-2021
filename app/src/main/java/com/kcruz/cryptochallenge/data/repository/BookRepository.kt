package com.kcruz.cryptochallenge.data.repository

import com.kcruz.cryptochallenge.data.source.remote.IBookRemoteSource
import com.kcruz.cryptochallenge.usecase.data.IBookRepository

class BookRepository(private val remoteSource: IBookRemoteSource): IBookRepository {

    override suspend fun getAvailableBooks() = remoteSource.getAvailableBooks()

    override suspend fun getBookInfo(book: String) = remoteSource.getBookInfo(book)

    override suspend fun getOpenOrders(book: String, aggregate: Boolean?) = remoteSource.getOpenOrders(book, aggregate)

}