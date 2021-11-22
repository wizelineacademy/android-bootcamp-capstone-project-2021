package dev.ricsarabia.cryptochallenge.core

import android.app.Application
import androidx.room.Room
import dev.ricsarabia.cryptochallenge.data.db.AppDatabase
import dev.ricsarabia.cryptochallenge.data.repos.BitsoRepo

/**
 * Created by Ricardo Sarabia on 2021/11/19.
 */
class CryptoChallengeApp : Application() {
    private val database by lazy {
        Room.databaseBuilder(this, AppDatabase::class.java, "app_database").build()
    }
    val repository by lazy { BitsoRepo(database) }
}