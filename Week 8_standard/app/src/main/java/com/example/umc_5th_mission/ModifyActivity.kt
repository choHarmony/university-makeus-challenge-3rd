package com.example.umc_5th_mission

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.umc_5th_mission.databinding.ActivityMemoBinding
import com.example.umc_5th_mission.databinding.ActivityModifyBinding

class ModifyActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityModifyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityModifyBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        // 메모장 메인에서 넘어온 title, content를 edittext에 넣음
        val bundle = intent.extras

        val exTitle = bundle?.getString("exTitle")
        val exContent = bundle?.getString("exContent")
        val position = bundle?.getString("position")
        val favorite = bundle?.getBoolean("like?")

        viewBinding.modifyTitle.setText(exTitle.toString())
        viewBinding.modifyMemo.setText(exContent.toString())
        viewBinding.btnFavorite.isChecked = favorite!!


        // + 버튼을 누르면 메모가 수정되도록 메인에 현재 editText의 내용을 보내고 수정 액티비티 끝내기
        viewBinding.btnModify.setOnClickListener {
            val intent = Intent(this, MemoActivity::class.java)
            intent.putExtra("modifyTitle", viewBinding.modifyTitle.text.toString())
            intent.putExtra("modifyContent", viewBinding.modifyMemo.text.toString())
            intent.putExtra("position", position)

            // favorite toggle의 활성화 여부에 따라 보내는 boolean 값 다르게 하기
            if (viewBinding.btnFavorite.isChecked) {
                intent.putExtra("memoFavorite", true)
            }
            else {
                intent.putExtra("memoFavorite", false)
            }

            setResult(RESULT_OK, intent)
            finish()
        }
    }
}