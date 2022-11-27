package com.example.umc_5th_mission

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.umc_5th_mission.databinding.ActivityMemoBinding

class MemoActivity : AppCompatActivity() { // 왜 open class로 바뀌어있었지? 문제 생기면 open class로 다시 바꾸자

    private lateinit var viewBinding: ActivityMemoBinding
    lateinit var activityResultLauncher: ActivityResultLauncher<Intent>
    var mTitle: String = ""
    var mContent: String = ""

    var memoList = arrayListOf(
        memoData("환영합니다!", "우상단 + 버튼을 눌러 메모를 작성해보세요.", memoFavorite = false)
    )
    val adapter = memoAdapter(memoList)

    var modify: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivityMemoBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        // RecyclerView
        viewBinding.memoRecyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        viewBinding.memoRecyclerView.setHasFixedSize(true)
        viewBinding.memoRecyclerView.adapter = memoAdapter(memoList)

        // roomdb 이용해 여태까지 저장된 메모들 보여주기 (껐다 켜도 사라지지 않게)
        val roomDb = memoDataBase.getInstance(this)
        val memoo = roomDb!!.memoDao().selectAll()

        // sharedPreference를 이용해 메모 즐겨찾기 저장
        val sharedPrefs = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val editor = sharedPrefs.edit()

        if (roomDb != null) {
            val allMemo = roomDb.memoDao().selectAll()

            for (i in 0 until allMemo.size) {
                val isFavorite = sharedPrefs.getBoolean(memoo[i].memoId.toString(), false)
                memoList.add(memoData(allMemo[i].title, allMemo[i].content, isFavorite))
            }
            adapter.notifyItemInserted(memoAdapter(memoList).itemCount)
        }

        // 메모 작성 화면에서 넘어온 데이터 받아서 recyclerview에 추가 or 수정
        activityResultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (modify == false) {
                    if (result.resultCode == RESULT_OK) {
                        mTitle = result.data?.getStringExtra("title").toString()
                        mContent = result.data?.getStringExtra("content").toString()

                        roomDb.memoDao().insert(Memo(mTitle, mContent))
                        memoList.add(memoData(mTitle, mContent, memoFavorite = false))
                    }
                }
                else {
                    if (result.resultCode == RESULT_OK) {
                        val newTitle = result.data?.getStringExtra("modifyTitle").toString()
                        val newContent = result.data?.getStringExtra("modifyContent").toString()
                        val position = result.data?.getStringExtra("position").toString().toInt()
                        val favorite = result.data?.getBooleanExtra("memoFavorite", false)

                        roomDb.memoDao().updateTitleByMemoId(memoo[position-1].memoId, newTitle)
                        roomDb.memoDao().updateContentByMemoId(memoo[position-1].memoId, newContent)

                        // favorite 버튼 check 여부에 따라 sharedPrefs에 저장
                        editor.putBoolean(memoo[position-1].memoId.toString(), favorite!!).apply()

                        memoList[position].memoTitle = newTitle
                        memoList[position].memoContent = newContent
                        adapter.notifyItemChanged(position)
                    }
                }
            }

        // 메모 작성 화면으로 넘어가기
        viewBinding.btnAddMemo.setOnClickListener {
            val mIntent = Intent(this, WriteActivity::class.java)
            modify = false
            activityResultLauncher.launch(mIntent)
        }

        // 즐겨찾는 메모 보관함으로 넘어가기
        viewBinding.btnFavoriteInventory.setOnClickListener {
            val intent = Intent(this, InventoryActivity::class.java)
            startActivity(intent)
        }


        // 메모 리스트 recyclerview click 이벤트

        // 메모 item 클릭 시 modifyIntent로 메모 내용과 제목과 position 넘기기
        val modifyIntent = Intent(this, ModifyActivity::class.java)
        adapter.setItemClickListener(object : memoAdapter.onItemClickListener {
            override fun onClick(v: View, position: Int) {
                val item = memoList[position]

                modifyIntent.putExtra("exTitle", item.memoTitle)
                modifyIntent.putExtra("exContent", item.memoContent)
                modifyIntent.putExtra("position", position.toString())
                modifyIntent.putExtra("like?", sharedPrefs.getBoolean(memoo[position-1].memoId.toString(), true))

                modify = true

                activityResultLauncher.launch(modifyIntent)
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
                            if (roomDb != null) {
                                val t = roomDb.memoDao().selectByMemoTitle(memoList[position].memoTitle)
                                roomDb.memoDao().delete(t)
                                memoList.removeAt(position)
                                adapter.notifyItemRemoved(position)
                                adapter.notifyItemRangeChanged(position, memoList.size)
                            }
                        })
                    .setNegativeButton("아니요",
                        DialogInterface.OnClickListener { dialog, id ->

                        })
                builder.show()
            }
        })
        viewBinding.memoRecyclerView.adapter = adapter

    }

}