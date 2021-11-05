package com.serranoie.crypteck.domain.usecases.trade

import com.serranoie.crypteck.domain.repositories.TradeRepository
import com.serranoie.crypteck.domain.usecases.UseCaseResult

class GetRecentTradesUseCase(private val tradeRepository: TradeRepository) {

    operator fun invoke(book: String): UseCaseResult {
        return tradeRepository.getRecentTrades(book)
    }

}