package com.serranoie.data.crypteck.repository.trade

import androidx.annotation.WorkerThread
import com.serranoie.crypteck.domain.repositories.TradeRepository
import com.serranoie.crypteck.domain.usecases.UseCaseResult
import com.serranoie.data.crypteck.errors.ErrorCodes
import com.serranoie.data.crypteck.networking.BitsoApi
import java.lang.Exception
import javax.inject.Inject

class RemoteTradeRepositoryImpl @Inject constructor(private val bitsoApi: BitsoApi): TradeRepository {

    @WorkerThread
    override fun getTickers(book: String): UseCaseResult {
        try {
            val response = bitsoApi.getRecentTrades(book).execute()

            if(response.isSuccessful) {
                val tradeDto = response.body()
                    ?: return UseCaseResult.Error(ErrorCodes.OK_BUT_INFO_NOT_AVAILABLE)

                return UseCaseResult.Success(tradeDto)
            } else {
                return UseCaseResult.Error(response.code())
            }
        } catch (e: Exception) {
            return UseCaseResult.Error(ErrorCodes.EXCEPTION_ON_REQUEST, e)
        }
    }

    @WorkerThread
    override fun getRecentTrades(book: String): UseCaseResult {
        try {
            val response = bitsoApi.getRecentTrades(book).execute()

            if(response.isSuccessful) {
                val tradeDto = response.body()
                    ?: return UseCaseResult.Error(ErrorCodes.OK_BUT_INFO_NOT_AVAILABLE)

                return UseCaseResult.Success(tradeDto)
            } else {
                return UseCaseResult.Error(response.code())
            }
        } catch (e: Exception) {
            return UseCaseResult.Error(ErrorCodes.EXCEPTION_ON_REQUEST, e)
        }
    }
}