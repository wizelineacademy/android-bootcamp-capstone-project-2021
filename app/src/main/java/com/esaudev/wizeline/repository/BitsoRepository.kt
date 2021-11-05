package com.esaudev.wizeline.repository

import com.esaudev.wizeline.model.AvailableBook
import com.esaudev.wizeline.utils.DataState

interface BitsoRepository {

    suspend fun getAvailableBooks(): DataState<List<AvailableBook>>
}