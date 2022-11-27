package com.example.umc_5th_mission

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity // 이게 룸디비에서 사용되는 객체다 라는 것을 알려줌
data class Memo( // memo 객체 생성 완료
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "content") val content: String,
    // primary key - 동일한 값이 하나만 들어갈 수 있는 고유 키
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "memoId") val memoId: Int = 0
)
