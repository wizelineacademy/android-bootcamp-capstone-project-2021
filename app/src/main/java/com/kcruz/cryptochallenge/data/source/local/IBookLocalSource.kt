package com.kcruz.cryptochallenge.data.source.local

import com.kcruz.cryptochallenge.domain.ExchangeOrderBook

interface IBookLocalSource {

    fun getExchangeOrderBooks(): List<ExchangeOrderBook>

    fun saveExchangeOrderBooks(exchangeOrderBooks: List<ExchangeOrderBook>)

}