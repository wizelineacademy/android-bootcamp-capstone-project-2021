package com.kcruz.cryptochallenge.framework.source.remote

import com.kcruz.cryptochallenge.commons.Response
import com.kcruz.cryptochallenge.data.source.remote.IBookRemoteSource
import com.kcruz.cryptochallenge.framework.client.ApiClient

class BookRemoteSource : IBookRemoteSource {

    override suspend fun getAvailableBooks(): Response {
        val response = ApiClient().getService()!!.getAvailableBooks()
        return Response(response.code(), response.body())
    }

    override suspend fun getBookInfo(book: String): Response {
        val params = HashMap<String, String>()
        params[BOOK_PARAM] = book
        val response = ApiClient().getService()!!.getBookInfo(params)
        return Response(response.code(), response.body())
    }

    override suspend fun getOpenOrders(book: String, aggregate: Boolean?): Response {
        val params = HashMap<String, String>()
        params[BOOK_PARAM] = book
        if (aggregate != null) params[AGGREGATE_PARAM] = aggregate.toString()
        val response = ApiClient().getService()!!.getOpenOrders(params)
        return Response(response.code(), response.body())
    }

    companion object {
        const val BOOK_PARAM = "book"
        const val AGGREGATE_PARAM = "aggregate"
    }

}