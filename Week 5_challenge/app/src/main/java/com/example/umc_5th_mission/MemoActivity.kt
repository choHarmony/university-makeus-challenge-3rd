package com.example.umc_5th_mission

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Adapter
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.umc_5th_mission.databinding.ActivityMemoBinding
import org.w3c.dom.Text

open class MemoActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityMemoBinding
    lateinit var activityResultLauncher: ActivityResultLauncher<Intent>

    val memoList = arrayListOf(
        memoData("환영합니다!", "우상단 + 버튼을 눌러 메모를 작성해보세요.")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivityMemoBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        // RecyclerView
        viewBinding.memoRecyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        viewBinding.memoRecyclerView.setHasFixedSize(true)
        viewBinding.memoRecyclerView.adapter = memoAdapter(memoList)

        // 메모 작성 화면으로 넘어가기
        viewBinding.btnAddMemo.setOnClickListener {
            val intent = Intent(this, WriteActivity::class.java)
            startActivity(intent)
        }

        // 메모 작성 화면에서 넘어온 데이터 받아서 recyclerview에 추가
        activityResultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
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

        // 메모 리스트 recyclerview click 이벤트
        val adapter = memoAdapter(memoList)
        val modifyIntent = Intent(this, ModifyActivity::class.java)
        adapter.setItemClickListener(object : memoAdapter.onItemClickListener {
            override fun onClick(v: View, position: Int) {
                val item = memoList[position]

                modifyIntent.putExtra("exTitle", item.memoTitle)
                modifyIntent.putExtra("exContent", item.memoContent)

                startActivityForResult(modifyIntent, 1)
                memoList.removeAt(position)
                adapter.notifyItemChanged(position)
                adapter.notifyItemRangeChanged(position, memoList.size)

            }
        })

        adapter.setItemLongClickListener(object : memoAdapter.onItemLongClickListener {
            override fun onLongClick(v: View, position: Int) {
                val itemLong = memoList[position]

                val builder = AlertDialog.Builder(this@MemoActivity)
                builder.setTitle("알림")
                    .setMessage("메모를 삭제하시겠습니까?")
                    .setPositiveButton("예",
                        DialogInterface.OnClickListener { dialog, id ->
                            memoList.removeAt(position)
                            adapter.notifyItemChanged(position)
                            adapter.notifyItemRangeChanged(position, memoList.size)
                        })
                    .setNegativeButton("아니요",
                        DialogInterface.OnClickListener { dialog, id ->

                        })
                builder.show()
            }
        })
        viewBinding.memoRecyclerView.adapter = adapter

    }

    // ModifyActivity에서 수정한 데이터 받아오기
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK) {
            val newTitle = data?.getStringExtra("modifyTitle")
            val newContent = data?.getStringExtra("modifyContent")

            if (newTitle != null && newContent != null) {
                memoList.add(memoData(newTitle.toString(), newContent.toString()))
            }
        }
    }

}