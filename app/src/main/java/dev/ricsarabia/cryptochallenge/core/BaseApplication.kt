package dev.ricsarabia.cryptochallenge.core

import android.app.Application
import androidx.room.Room
import dev.ricsarabia.cryptochallenge.data.db.Database

/**
 * Created by Ricardo Sarabia on 2021/11/19.
 */
class BaseApplication : Application() {
    lateinit var db: Database
        private set

    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(this, Database::class.java, "db").build()
    }
}