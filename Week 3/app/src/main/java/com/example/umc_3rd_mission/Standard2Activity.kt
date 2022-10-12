package com.example.umc_3rd_mission

import android.app.Instrumentation
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.umc_3rd_mission.databinding.ActivityStandard2Binding
import com.example.umc_3rd_mission.databinding.ActivityStandardBinding

class Standard2Activity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityStandard2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityStandard2Binding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        val bundle = intent.extras

        if (bundle != null) {
            val value = bundle.getString("key", "")
            viewBinding.textReceive.text = value
            //Toast.makeText(this, value, Toast.LENGTH_LONG).show()
        }

        viewBinding.btnGo2.setOnClickListener {
            val intent = Intent(this, Standard3Activity::class.java)
            startActivity(intent)
        }

    }


}