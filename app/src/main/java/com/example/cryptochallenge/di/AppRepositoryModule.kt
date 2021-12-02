package com.example.cryptochallenge.di

import com.example.cryptochallenge.domain.BookRepository
import com.example.cryptochallenge.domain.BookRepositoryImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class AppRepositoryModule {
    @Binds
    abstract fun bindBookRepository(
        bookRepositoryImp: BookRepositoryImp
    ): BookRepository
}