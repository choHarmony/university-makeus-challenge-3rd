package com.example.umc_5th_mission

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Memo::class], version = 1)
abstract class memoDataBase: RoomDatabase() {
    abstract fun memoDao(): memoDao

    companion object {
        // 변수에 직접적으로 접근하는 걸 막기 위해 private 적음
        // 나중에 초기화하는 과정은 여기를 통해 진행되기 때문에 null 허용
        private var memosdata: memoDataBase? = null

        @Synchronized
        // 공유 자원의 문제: 여러 스레드에서 동시에 하나의 자원에 접근하고자 할 때
        // 그걸 방지하기 위해 이를 통해 '내가 이걸 사용하고 있다'를 보여줌줌
        fun getInstance(context: Context): memoDataBase? {
            if (memosdata == null) {
                synchronized(memoDataBase::class.java) {
                    memosdata = Room.databaseBuilder(
                        context.applicationContext,
                        memoDataBase::class.java,
                        "memo-database" // 여러개 만드는 경우 이름 겹치면 안됨
                    ).allowMainThreadQueries().build()
                }
            }
            return memosdata
        }

    }
}