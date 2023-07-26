package com.baymax104.bookmanager20compose.repo

import android.app.Application
import androidx.room.Room
import com.baymax104.bookmanager20compose.repo.database.Database
import com.baymax104.bookmanager20compose.repo.database.LocalDatabase

/**
 * Application
 * @author John
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()

        Database = Room
            .databaseBuilder(
                this,
                LocalDatabase::class.java,
                LocalDatabase.DatabaseName
            )
            .fallbackToDestructiveMigration()
            .build()
    }
}