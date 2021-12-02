package com.alexbar10.cryptotrack.di

import android.content.Context
import androidx.room.Room
import com.alexbar10.cryptotrack.database.CryptoDatabase
import com.alexbar10.cryptotrack.utils.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext app: Context
    ): CryptoDatabase {
        return Room.databaseBuilder(
            app,
            CryptoDatabase::class.java,
            DATABASE_NAME
        )
            .fallbackToDestructiveMigrationOnDowngrade()
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideCryptoDao(db: CryptoDatabase) = db.cryptoDatabaseDao()
}
