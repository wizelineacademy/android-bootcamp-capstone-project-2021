package com.example.cryptochallenge.usecases

import com.example.cryptochallenge.data.repository.CryptoRepository

/**
 * Use case that get order book of a specific record
 *
 * @property cryptoRepository Instance of [CryptoRepository]
 */
class GetOrderBook(private val cryptoRepository: CryptoRepository) {

    /**
     * Execute use case that get order book of a specific record
     *
     * @return Web service response
     */
    fun execute(bookName: String) = cryptoRepository.getOrderBook(bookName)
}