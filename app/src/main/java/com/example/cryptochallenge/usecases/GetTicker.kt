package com.example.cryptochallenge.usecases

import com.example.cryptochallenge.data.repository.CryptoRepository

/**
 * Use case that get ticker of specific book
 *
 * @property cryptoRepository Instance of [CryptoRepository]
 */
class GetTicker(private val cryptoRepository: CryptoRepository) {

    /**
     * Execute use case that get ticker of specific book
     *
     * @return Web service response
     */
    fun execute(bookName: String) =
        cryptoRepository.getTicker(bookName)
}