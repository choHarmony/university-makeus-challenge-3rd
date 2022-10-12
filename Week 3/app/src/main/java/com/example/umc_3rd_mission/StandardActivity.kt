package com.example.umc_3rd_mission

import android.app.Instrumentation.ActivityResult
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.umc_3rd_mission.databinding.ActivityStandardBinding

class StandardActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityStandardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityStandardBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.btnGo.setOnClickListener {
            val intent = Intent(this, Standard2Activity::class.java)
            intent.putExtra("key", viewBinding.putAnything.getText().toString())
            startActivity(intent)
        }

    }
}