package com.kcruz.cryptochallenge

import android.app.Application

class App: Application() {

    companion object {
        @Volatile
        lateinit var appContext: Application
    }

    override fun onCreate() {
        super.onCreate()
        appContext = this
    }
}