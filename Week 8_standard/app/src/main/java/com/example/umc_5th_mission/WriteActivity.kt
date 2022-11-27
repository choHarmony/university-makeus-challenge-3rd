package com.example.umc_5th_mission

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.viewbinding.ViewBinding
import com.example.umc_5th_mission.databinding.ActivityWriteBinding

class WriteActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityWriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivityWriteBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.btnAdd.setOnClickListener {
            val intent = Intent(this, MemoActivity::class.java).apply {
                putExtra("title", viewBinding.putTitle.text.toString())
                putExtra("content", viewBinding.putMemo.text.toString())
            }

            if (viewBinding.putTitle.length() == 0 && viewBinding.putMemo.length() == 0) {
                val builder = AlertDialog.Builder(this)
                builder.setTitle("알림")
                    .setMessage("메모 작성을 취소하시겠습니까?")
                    .setPositiveButton("예",
                        DialogInterface.OnClickListener { dialog, id ->
                            finish()
                        })
                    .setNegativeButton("아니요",
                        DialogInterface.OnClickListener { dialog, id ->

                        })
                builder.show()
            }
            else {
                setResult(RESULT_OK, intent)
                if (!isFinishing) finish()
            }
        }
    }
}