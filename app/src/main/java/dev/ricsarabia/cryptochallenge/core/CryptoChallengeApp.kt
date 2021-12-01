package dev.ricsarabia.cryptochallenge.core

import android.app.Application
import androidx.room.Room
import dagger.hilt.android.HiltAndroidApp
import dev.ricsarabia.cryptochallenge.data.db.AppDatabase
import dev.ricsarabia.cryptochallenge.data.repos.BitsoRepo
import dev.ricsarabia.cryptochallenge.di.BitsoNetworkingModule

/**
 * Created by Ricardo Sarabia on 2021/11/19.
 */
@HiltAndroidApp
class CryptoChallengeApp : Application() {
    private val database by lazy {
        Room.databaseBuilder(this, AppDatabase::class.java, "app_database").build()
    }
    val repository by lazy { BitsoRepo(database, BitsoNetworkingModule.service) }
}