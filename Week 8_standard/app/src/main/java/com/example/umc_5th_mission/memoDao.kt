package com.example.umc_5th_mission

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface memoDao {
    @Insert
    fun insert(title: Memo) // 위에 어노테이션 쓰면 알아서 작성해줌

    @Delete
    fun delete(title: List<Memo>)

    // 조회
    @Query("SELECT * FROM MEMO")
    fun selectAll(): List<Memo>

    @Query("SELECT * FROM Memo WHERE memoId = :memoId")
    fun selectMemoId(memoId: Int): Memo

    @Query("SELECT * FROM Memo WHERE title = :title")
    fun selectByMemoTitle(title: String): List<Memo>

    // 업데이트
    @Query("UPDATE Memo SET title = :title WHERE memoId = :memoId")
    fun updateTitleByMemoId(memoId: Int, title: String)

    @Query("UPDATE Memo SET content = :content WHERE memoId = :memoId")
    fun updateContentByMemoId(memoId: Int, content: String)
}