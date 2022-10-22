package com.example.umc_4th_mission

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.example.umc_4th_mission.databinding.ActivityMainBinding
import java.util.logging.Logger.global

class MainActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityMainBinding
    lateinit var memoText: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.btnAddMemo.setOnClickListener {
            val intent = Intent(this, SeeActivity::class.java)

            // 다음 화면으로 메모한 내용 넘기고 intent로 다음 화면 띄우기
            intent.putExtra("memoContent", viewBinding.putMemo.text.toString())
            startActivity(intent)
        }

    }

    override fun onStop() {
        memoText = viewBinding.putMemo.text.toString()
        viewBinding.putMemo.setText(null)
        super.onStop()
    }

    override fun onRestart() {
        AlertDialog.Builder(this)
            .setTitle("알림")
            .setMessage("이어서 작성하시겠습니까?")
            .setPositiveButton("예", object : DialogInterface.OnClickListener {
                override fun onClick(p0: DialogInterface?, p1: Int) {
                    viewBinding.putMemo.setText(memoText)
                }
            })
            .setNegativeButton("아니오", object : DialogInterface.OnClickListener {
                override fun onClick(p0: DialogInterface?, p1: Int) {
                    memoText = null.toString()
                    viewBinding.putMemo.setText(null)
                }
            })
            .create()
            .show()
        super.onRestart()
    }


}