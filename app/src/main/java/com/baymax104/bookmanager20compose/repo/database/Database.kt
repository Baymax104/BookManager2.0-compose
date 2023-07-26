package com.baymax104.bookmanager20compose.repo.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.baymax104.bookmanager20compose.entity.Book
import com.baymax104.bookmanager20compose.util.RoomConverter

/**
 * Room数据库
 * @author John
 */
@TypeConverters(RoomConverter::class)
@Database(entities = [Book::class], version = 1, exportSchema = false)
abstract class LocalDatabase : RoomDatabase() {

    companion object {
        const val DatabaseName = "BookManager"
    }

    abstract fun bookMapper(): BookMapper

}

lateinit var Database: LocalDatabase