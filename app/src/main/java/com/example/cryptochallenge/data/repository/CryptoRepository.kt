package com.example.cryptochallenge.data.repository

import com.example.cryptochallenge.data.source.ICryptoSource
import com.example.cryptochallenge.di.source.CryptoSource

/**
 * Repository for cryptocurrencies
 */
class CryptoRepository {
    /**
     * Property to handle requests
     */
    private val source: ICryptoSource = CryptoSource()

    /**
     * Get all the available books
     *
     * @return [LiveData] object with available books info or null if error
     */
    fun getAvailableBooks() = source.getAvailableBooks()

    /**
     * Get trading information of a specific book
     *
     * @param bookName Name of the book
     * @return [LiveData] object with ticker information or null if error
     */
    fun getTicker(bookName: String) = source.getTicker(bookName)

    /**
     * Get all open books orders of a specific book
     *
     * @param bookName Name of the book
     * @return [LiveData] object with order book info or null if error
     */
    fun getOrderBook(bookName: String) = source.getOrderBook(bookName)

    /**
     * Clean the disposable property
     */
    fun clear() = source.clear()
}