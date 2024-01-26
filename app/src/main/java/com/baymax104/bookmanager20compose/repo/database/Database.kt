package com.baymax104.bookmanager20compose.repo.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.withTransaction
import com.baymax104.bookmanager20compose.bean.entity.BookEntity
import com.baymax104.bookmanager20compose.bean.entity.HistoryEntity
import com.baymax104.bookmanager20compose.util.RoomConverter
import com.blankj.utilcode.util.Utils

/**
 * Room数据库
 * @author John
 */
@TypeConverters(RoomConverter::class)
@Database(entities = [BookEntity::class, HistoryEntity::class], version = 4, exportSchema = false)
abstract class LocalDatabase : RoomDatabase() {

    companion object {
        const val DatabaseName = "BookManager"

        val Database: LocalDatabase = Room
            .databaseBuilder(
                Utils.getApp(),
                LocalDatabase::class.java,
                DatabaseName
            )
            .fallbackToDestructiveMigration()
            .build()
    }

    abstract fun bookDao(): BookDao

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

/**
 * 创建数据库Mapper实例
 * @param T Mapper类型
 * @return Mapper实例
 */
inline fun <reified T> dao(): Lazy<T> = lazy { LocalDatabase.Database.create() }

/**
 * 事务处理
 *
 * @param R 返回值类型
 * @param block 处理块
 * @return 块返回值
 */
suspend fun <R> transaction(block: suspend () -> R): R = LocalDatabase.Database.withTransaction(block)
