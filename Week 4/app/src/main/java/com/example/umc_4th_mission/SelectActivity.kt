package com.example.umc_4th_mission

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.umc_4th_mission.databinding.ActivitySelectBinding

class SelectActivity : AppCompatActivity() {

    private lateinit var viewbinding: ActivitySelectBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewbinding = ActivitySelectBinding.inflate(layoutInflater)
        setContentView(viewbinding.root)

        viewbinding.btnText.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        viewbinding.btnPaint.setOnClickListener {
            val intent = Intent(this, PaintActivity::class.java)
            startActivity(intent)
        }
    }
}