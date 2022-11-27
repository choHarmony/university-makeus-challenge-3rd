package com.example.umc_5th_mission

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.umc_5th_mission.databinding.ActivityInventoryBinding
import com.example.umc_5th_mission.databinding.ActivityMemoBinding

class InventoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInventoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityInventoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val favoriteMemoList: ArrayList<memoData> = arrayListOf()

        // recyclerview 연결
        binding.favoriteMemoRecyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.favoriteMemoRecyclerView.setHasFixedSize(true)
        binding.favoriteMemoRecyclerView.adapter = memoAdapter(favoriteMemoList)

        // RoomDB를 통해 메모 제목과 내용 불러오기
        val roomDb = memoDataBase.getInstance(this)
        val memoo = roomDb!!.memoDao().selectAll()

        val sharedPrefsFavorites = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        for (i in 0 until memoo.size) {
            val isFavorite = sharedPrefsFavorites.getBoolean(memoo[i].memoId.toString(), false)
            if (isFavorite) {
                favoriteMemoList.add(memoData(memoo[i].title, memoo[i].content, isFavorite))
            }
        }

    }
}