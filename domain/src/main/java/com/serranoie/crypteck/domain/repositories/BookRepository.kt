package com.serranoie.crypteck.domain.repositories

import com.serranoie.crypteck.domain.usecases.UseCaseResult

interface BookRepository {
    fun getAvailableBooks(): UseCaseResult
    fun getOpenOrdersBook(book: String): UseCaseResult
    fun getTickers(book: String): UseCaseResult
    fun getREcentTrades(book: String): UseCaseResult
}