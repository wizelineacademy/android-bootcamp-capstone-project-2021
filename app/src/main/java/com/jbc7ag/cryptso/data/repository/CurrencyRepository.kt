package com.jbc7ag.cryptso.data.repository

import com.jbc7ag.cryptso.data.model.AvailableBooks
import com.jbc7ag.cryptso.data.services.CurrencyApi
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CurrencyRepository  @Inject constructor(
        private val client: CurrencyApi) {
        suspend fun getAvailableBooks(): Response<AvailableBooks> = client.getAvailableBooks()
    }
