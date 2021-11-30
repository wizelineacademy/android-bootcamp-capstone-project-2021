package com.example.bootcampproject

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.bootcampproject.data.local.BitsoAppDataBase
import com.example.bootcampproject.data.services.BitsoServices
import com.squareup.moshi.Moshi
import org.junit.runner.RunWith

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val CURRENCY_BASE_URL = "https://api.bitso.com/v3/"

open class ConnectRetrofitAndRoom {
    fun createRetrofitInstance(): BitsoServices {
        val moshi = Moshi.Builder().build()
        val moshiConverterFactory = MoshiConverterFactory.create(moshi)
        return Retrofit.Builder()
            .addConverterFactory(moshiConverterFactory)
            .baseUrl(CURRENCY_BASE_URL)
            .build()
            .create(BitsoServices::class.java)
    }

    fun createRoomInstance(): BitsoAppDataBase {
        val context = ApplicationProvider.getApplicationContext<Context>()
        return Room.inMemoryDatabaseBuilder(
            context, BitsoAppDataBase::class.java
        ).build()
    }

}