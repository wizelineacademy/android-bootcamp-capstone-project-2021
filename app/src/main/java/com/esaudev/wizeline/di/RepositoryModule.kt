package com.esaudev.wizeline.di

import com.esaudev.wizeline.data.remote.sources.BitsoRemoteDataSource
import com.esaudev.wizeline.data.remote.sources.BitsoRemoteDataSourceImpl
import com.esaudev.wizeline.repository.BitsoRepository
import com.esaudev.wizeline.repository.BitsoRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

/*@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindBitsoRepository(
        bitsoRepository: BitsoRepositoryImpl
    ): BitsoRepository

    @Binds
    abstract fun bindRemoteDataSource(
        remoteDataSource: BitsoRemoteDataSourceImpl
    ): BitsoRemoteDataSource

}*/