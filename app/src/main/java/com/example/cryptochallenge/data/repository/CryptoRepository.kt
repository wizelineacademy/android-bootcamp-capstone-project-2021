package com.example.cryptochallenge.data.repository

import com.example.cryptochallenge.data.source.ICryptoSource
import com.example.cryptochallenge.di.source.CryptoSource

class CryptoRepository {
    private val source: ICryptoSource = CryptoSource()

    fun getAvailableBooks() = source.getAvailableBooks()

    fun getTicker(bookName: String) = source.getTicker(bookName)

    fun getOrderBook(bookName: String) = source.getOrderBook(bookName)

    fun clear() = source.clear()
}