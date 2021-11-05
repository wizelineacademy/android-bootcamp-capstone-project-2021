package com.esaudev.wizeline.data.remote.sources

import android.util.Log
import com.esaudev.wizeline.data.remote.api.BitsoApi
import com.esaudev.wizeline.data.remote.responses.mapToDomain
import com.esaudev.wizeline.model.AvailableBook
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
            Log.d("TAG_REMOTE", availableBooks.toString())
            DataState.Success(availableBooks)
        } else {
            DataState.Error(NETWORK_UNKNOWN_ERROR)
        }
    }


}