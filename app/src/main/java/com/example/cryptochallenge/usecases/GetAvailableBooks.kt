package com.example.cryptochallenge.usecases

import com.example.cryptochallenge.data.repository.CryptoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetAvailableBooks(private val repository: CryptoRepository) {
    fun execute() = repository.getAvailableBooks()
}