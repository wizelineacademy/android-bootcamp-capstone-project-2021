package com.serranoie.data.crypteck.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class RemoteGetAvailableBooks

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class RemoteGetOpenOrdersBook

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class RemoteGetTickers

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class RemoteGetRecentTrades

