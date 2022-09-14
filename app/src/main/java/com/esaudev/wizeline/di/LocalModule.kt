package com.esaudev.wizeline.di

import android.content.Context
import androidx.room.Room
import com.esaudev.wizeline.data.local.BitsoDao
import com.esaudev.wizeline.data.local.BitsoDatabase
import com.esaudev.wizeline.data.local.sources.BitsoLocalDataSource
import com.esaudev.wizeline.data.local.sources.BitsoLocalDataSourceImpl
import com.esaudev.wizeline.utils.Constants.BITSO_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Singleton
    @Provides
    fun provideBitsoDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(context, BitsoDatabase::class.java, BITSO_DATABASE).build()

    @Singleton
    @Provides
    fun provideBitsoDao(db: BitsoDatabase) = db.bitsoDao()

    @Singleton
    @Provides
    fun provideBitsoLocalDataSource(
        bitsoDao: BitsoDao
    ): BitsoLocalDataSource = BitsoLocalDataSourceImpl(
        bitsoDao
    )
}
