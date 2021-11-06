package com.esaudev.wizeline.data.remote.sources

import android.util.Log
import com.esaudev.wizeline.data.remote.api.BitsoApi
import com.esaudev.wizeline.data.remote.responses.mapToDomain
import com.esaudev.wizeline.model.AvailableBook
import com.esaudev.wizeline.model.OrderBook
import com.esaudev.wizeline.model.Ticker
import com.esaudev.wizeline.utils.Constants.NETWORK_UNKNOWN_ERROR
import com.esaudev.wizeline.utils.DataState
import javax.inject.Inject

class BitsoRemoteDataSourceImpl @Inject constructor(
    private val bitsoApi: BitsoApi
): BitsoRemoteDataSource {

    override suspend fun getAvailableBooks(): DataState<List<AvailableBook>> {
        val response = bitsoApi.getAvailableBooks()

        return if (response.isSuccessful){
            val availableBooks = response.body()?.payload?.mapToDomain() ?: emptyList()
            DataState.Success(availableBooks)
        } else {
            DataState.Error(NETWORK_UNKNOWN_ERROR)
        }
    }

    override suspend fun getTickerFromBook(book: String): DataState<Ticker> {
        return DataState.Error(NETWORK_UNKNOWN_ERROR) // TODO Hacer esto
    }

    override suspend fun getOrderBook(book: String): DataState<OrderBook> {

        return try {
            val response = bitsoApi.getOrderBook(book)

            if (response.isSuccessful){
                val orderBooks = response.body()?.payload?.mapToDomain()!!

                DataState.Success(orderBooks)
            } else {
                DataState.Error(NETWORK_UNKNOWN_ERROR)
            }
        } catch (e: Exception){
            DataState.Error(NETWORK_UNKNOWN_ERROR)
        }
    }
}