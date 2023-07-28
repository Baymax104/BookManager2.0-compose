package com.baymax104.bookmanager20compose.repo.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.baymax104.bookmanager20compose.entity.Book
import java.util.Date

/**
 * Book表Mapper
 * @author John
 */
@Dao
interface BookMapper {

    @Insert
    suspend fun insertProcess(book: Book): Long

    @Query("select * from Book where progress < 100 order by tableRank asc")
    suspend fun queryAllProcess(): MutableList<Book>

    @Query("select * from Book where progress >= 100 order by tableRank asc")
    suspend fun queryAllFinish(): MutableList<Book>

    @Query("delete from Book where id in (:bookIds)")
    suspend fun deleteBooks(bookIds: List<Int>): Int

    @Update
    suspend fun updateBookRank(books: List<Book>): Int

    @Query("update Book set progress = :progress, endTime = :endTime where id = :bookId")
    suspend fun updateBookProgress(bookId: Int, progress: Int, endTime: Date?): Int

}