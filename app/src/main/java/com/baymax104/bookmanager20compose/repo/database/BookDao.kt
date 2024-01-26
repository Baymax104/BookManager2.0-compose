package com.baymax104.bookmanager20compose.repo.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.baymax104.bookmanager20compose.bean.entity.BookEntity
import java.util.Date

/**
 * Book表Mapper
 * @author John
 */
@Dao
interface BookDao {

    @Insert
    suspend fun insertProgress(book: BookEntity): Long

    @Query("select * from Book where progress < 100 order by rank asc")
    fun queryAllProgress(): MutableList<BookEntity>

    @Query("select * from Book where progress >= 100 order by rank asc")
    suspend fun queryAllFinish(): MutableList<BookEntity>

    @Query("delete from Book where id in (:bookIds)")
    suspend fun deleteBooks(bookIds: List<Int>): Int

    @Query("select count(*) from Book where progress < 100")
    suspend fun countProgress(): Int

    @Update
    suspend fun updateBookRank(books: List<BookEntity>): Int

    @Query("update Book set progress = :progress, endTime = :endTime where id = :bookId")
    suspend fun updateBookProgress(bookId: Int, progress: Int, endTime: Date?): Int

}