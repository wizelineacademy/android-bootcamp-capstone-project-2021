package com.example.cryptochallenge.usecases

import com.example.cryptochallenge.data.repository.CryptoRepository

class GetOrderBook(private val cryptoRepository: CryptoRepository) {
    fun execute(bookName: String) = cryptoRepository.getOrderBook(bookName)
}