package com.serranoie.crypteck.domain.repositories

import com.serranoie.crypteck.domain.usecases.UseCaseResult

interface TradeRepository {
    fun getTickers(book: String): UseCaseResult
    fun getRecentTrades(book: String): UseCaseResult
}