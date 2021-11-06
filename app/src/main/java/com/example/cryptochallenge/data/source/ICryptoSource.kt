package com.example.cryptochallenge.data.source

import androidx.lifecycle.LiveData
import com.example.cryptochallenge.domain.availablebook.Payload

/**
 * Handle the calls to web services
 */
interface ICryptoSource {

    /**
     * Get all the available books
     *
     * @return [LiveData]<[List]<[Payload]>?> object with available books info or null if error
     */
    fun getAvailableBooks(): LiveData<List<Payload>?>

    /**
     * Get trading information of a specific book
     *
     * @param bookName Name of the book
     * @return [LiveData]<[com.example.cryptochallenge.domain.ticker.Payload]?> object with ticker
     * information or null if error
     */
    fun getTicker(bookName: String): LiveData<com.example.cryptochallenge.domain.ticker.Payload?>

    /**
     * Get all open books orders of a specific book
     *
     * @param bookName Name of the book
     * @return [LiveData]<[com.example.cryptochallenge.domain.orderbook.Payload]?> object with order
     * book info or null if error
     */
    fun getOrderBook(bookName: String): LiveData<com.example.cryptochallenge.domain.orderbook.Payload?>

    /**
     * Clean the disposable property
     */
    fun clear()
}