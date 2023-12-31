package com.baymax104.bookmanager20compose.repo.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.baymax104.bookmanager20compose.entity.Book
import com.baymax104.bookmanager20compose.entity.History
import com.baymax104.bookmanager20compose.util.RoomConverter
import com.blankj.utilcode.util.Utils

/**
 * Room数据库
 * @author John
 */
@TypeConverters(RoomConverter::class)
@Database(entities = [Book::class, History::class], version = 3, exportSchema = false)
abstract class LocalDatabase : RoomDatabase() {

    companion object {
        const val DatabaseName = "BookManager"
    }

    abstract fun bookMapper(): BookMapper

    inline fun <reified T> create(): T {
        val methods = javaClass.declaredMethods
        for (method in methods) {
            if (method.returnType == T::class.java) {
                method.isAccessible = true
                return method(this) as? T ?: throw IllegalArgumentException("Database Mapper not found")
            }
        }
        throw IllegalArgumentException("Database Mapper not found")
    }

}

val Database: LocalDatabase by lazy {
    Room
        .databaseBuilder(
            Utils.getApp(),
            LocalDatabase::class.java,
            LocalDatabase.DatabaseName
        )
        .fallbackToDestructiveMigration()
        .build()
}

/**
 * 创建数据库Mapper实例
 * @param T Mapper类型
 * @return Mapper实例
 */
inline fun <reified T> createMapper(): Lazy<T> = lazy { Database.create() }