package com.esaudev.wizeline.di

import com.esaudev.wizeline.data.local.BitsoDao
import com.esaudev.wizeline.data.local.sources.BitsoLocalDataSource
import com.esaudev.wizeline.data.remote.api.BitsoApi
import com.esaudev.wizeline.data.remote.sources.BitsoRemoteDataSource
import com.esaudev.wizeline.data.remote.sources.BitsoRemoteDataSourceImpl
import com.esaudev.wizeline.repository.BitsoRepository
import com.esaudev.wizeline.repository.BitsoRepositoryImpl
import com.esaudev.wizeline.utils.Constants.BITSO_BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideBitsoApi(): BitsoApi{
        return Retrofit.Builder()
            .baseUrl(BITSO_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BitsoApi::class.java)
    }

    @Provides
    @Singleton
    fun provideBitsoRepository(
        remoteDataSource: BitsoRemoteDataSource,
        localDataSource: BitsoLocalDataSource,
        networkDispatcher: CoroutineDispatcher
    ): BitsoRepository = BitsoRepositoryImpl(
        remoteDataSource,
        localDataSource,
        networkDispatcher
    )

    @Provides
    @Singleton
    fun provideBitsoRemoteDataSource(
        bitsoApi: BitsoApi
    ): BitsoRemoteDataSource = BitsoRemoteDataSourceImpl(
        bitsoApi
    )

    @Provides
    @Singleton
    fun provideNetworkDispatcher(): CoroutineDispatcher = Dispatchers.IO

}