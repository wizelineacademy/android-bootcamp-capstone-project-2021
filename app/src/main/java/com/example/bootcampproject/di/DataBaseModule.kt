package com.example.bootcampproject.di

import android.content.Context
import androidx.room.Room
import com.example.bootcampproject.data.local.BitsoAppDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {
    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext applicationContext: Context) =
        Room.databaseBuilder(
            applicationContext,
            BitsoAppDataBase::class.java, "BitsoDataBase"
        )
            .fallbackToDestructiveMigration()
            .build()

    @Singleton
    @Provides
    fun provideCurrencyDao(db: BitsoAppDataBase) = db.getCurrencyDao()

    @Singleton
    @Provides
    fun provideAvailableBooksDao(db: BitsoAppDataBase) = db.getAvailableBooks()

    @Singleton
    @Provides
    fun provideOrderBooks(db: BitsoAppDataBase) = db.getOrderBooks()

    @Singleton
    @Provides
    fun provideTickers(db: BitsoAppDataBase) = db.getTickers()
}
