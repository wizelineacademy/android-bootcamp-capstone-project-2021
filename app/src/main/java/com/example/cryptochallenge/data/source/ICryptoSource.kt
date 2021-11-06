package com.example.cryptochallenge.data.source

import androidx.lifecycle.LiveData
import com.example.cryptochallenge.domain.availablebook.Payload

interface ICryptoSource {
    fun getAvailableBooks(): LiveData<List<Payload>?>

    fun getTicker(bookName: String): LiveData<com.example.cryptochallenge.domain.ticker.Payload?>

    fun getOrderBook(bookName: String): LiveData<com.example.cryptochallenge.domain.orderbook.Payload?>

    fun clear()
}