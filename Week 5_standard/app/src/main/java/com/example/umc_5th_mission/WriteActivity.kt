package com.example.umc_5th_mission

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
            setResult(RESULT_OK, intent)
            if (!isFinishing) finish()
        }
    }
}