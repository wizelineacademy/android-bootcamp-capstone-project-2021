package com.serranoie.crypteck.domain.usecases.trade

import com.serranoie.crypteck.domain.repositories.TradeRepository
import com.serranoie.crypteck.domain.usecases.UseCaseResult
import javax.inject.Inject

open class GetRecentTradesUseCase @Inject constructor(private val tradeRepository: TradeRepository) {

    operator fun invoke(book: String): UseCaseResult {
        return tradeRepository.getRecentTrades(book)
    }

}