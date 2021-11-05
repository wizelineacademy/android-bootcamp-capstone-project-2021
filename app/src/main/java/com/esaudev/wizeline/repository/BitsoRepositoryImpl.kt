package com.esaudev.wizeline.repository

import com.esaudev.wizeline.data.remote.sources.BitsoRemoteDataSource
import com.esaudev.wizeline.model.AvailableBook
import com.esaudev.wizeline.utils.DataState
import javax.inject.Inject

class BitsoRepositoryImpl @Inject constructor(
    private val remoteDataSource: BitsoRemoteDataSource
) : BitsoRepository {

    override suspend fun getAvailableBooks(): DataState<List<AvailableBook>> =
        remoteDataSource.getAvailableBooks()

}