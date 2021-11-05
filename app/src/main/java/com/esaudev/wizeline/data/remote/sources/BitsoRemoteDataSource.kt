package com.esaudev.wizeline.data.remote.sources

import com.esaudev.wizeline.model.AvailableBook
import com.esaudev.wizeline.utils.DataState

interface BitsoRemoteDataSource {

    suspend fun getAvailableBooks(): DataState<List<AvailableBook>>

}