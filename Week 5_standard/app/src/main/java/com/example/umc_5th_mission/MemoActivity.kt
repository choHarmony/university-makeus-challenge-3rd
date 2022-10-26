package com.example.umc_5th_mission

import android.app.Activity.RESULT_OK
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.umc_5th_mission.databinding.ActivityMemoBinding

class MemoActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityMemoBinding
    lateinit var activityResultLauncher: ActivityResultLauncher<Intent>

    val memoList = arrayListOf(
        memoData("환영합니다!","우상단 + 버튼을 눌러 메모를 작성해보세요.")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivityMemoBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        // RecyclerView
        viewBinding.memoRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        viewBinding.memoRecyclerView.setHasFixedSize(true)
        viewBinding.memoRecyclerView.adapter = memoAdapter(memoList)

        // 메모 작성 화면으로 넘어가기
        viewBinding.btnAddMemo.setOnClickListener {
            val intent = Intent(this, WriteActivity::class.java)
            startActivity(intent)
        }

        // 메모 작성 화면에서 넘어온 데이터 받아서 recyclerview에 추가
        activityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val mTitle = result.data?.getStringExtra("title")
                val mContent = result.data?.getStringExtra("content")

                memoList.add(memoData(mTitle.toString(), mContent.toString()))

            }
        }

        viewBinding.btnAddMemo.setOnClickListener {
            val mIntent = Intent(this, WriteActivity::class.java)
            activityResultLauncher.launch(mIntent)
        }
    }

}
