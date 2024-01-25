package com.baymax104.bookmanager20compose.repo.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.baymax104.bookmanager20compose.bean.entity.HistoryEntity

/**
 *@Description
 *@Author John
 *@email
 *@Date 2023/4/14 23:38
 *@Version 1
 */
@Dao
interface HistoryDao {

    @Insert
    suspend fun insertHistory(history: HistoryEntity): Long

    @Query("select * from History where bookId = :bookId")
    suspend fun queryBookHistories(bookId: Int): MutableList<HistoryEntity>

    @Query("delete from History where bookId in (:bookIds)")
    suspend fun deleteBooksHistories(bookIds: List<Int>): Int

    @Update
    suspend fun updateHistoryDuplicate(histories: List<HistoryEntity>): Int
}