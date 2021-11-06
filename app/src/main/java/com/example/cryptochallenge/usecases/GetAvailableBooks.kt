package com.example.cryptochallenge.usecases

import com.example.cryptochallenge.data.repository.CryptoRepository

/**
 * Use case that get available books list
 *
 * @property repository Instance of [CryptoRepository]
 */
class GetAvailableBooks(private val repository: CryptoRepository) {

    /**
     * Execute use case that get available books list
     *
     * @return Web service response
     */
    fun execute() = repository.getAvailableBooks()
}