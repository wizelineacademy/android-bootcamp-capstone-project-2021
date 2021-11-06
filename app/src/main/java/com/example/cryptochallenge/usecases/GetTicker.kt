package com.example.cryptochallenge.usecases

import com.example.cryptochallenge.data.repository.CryptoRepository

class GetTicker(private val cryptoRepository: CryptoRepository) {
    fun execute(bookName: String) =
        cryptoRepository.getTicker(bookName)
}