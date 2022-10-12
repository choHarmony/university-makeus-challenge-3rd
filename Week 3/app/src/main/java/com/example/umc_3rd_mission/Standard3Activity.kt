package com.example.umc_3rd_mission

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.umc_3rd_mission.databinding.ActivityStandard3Binding

class Standard3Activity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityStandard3Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityStandard3Binding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        // 세번째 액티비티로 넘어왔을 때 기본적으로 보이는 뷰의 상태
        supportFragmentManager
            .beginTransaction()
            .replace(viewBinding.frameFragment.id, FirstFragment())
            .commitAllowingStateLoss()

        viewBinding.btnFragment1.setOnClickListener {
            supportFragmentManager
                .beginTransaction()
                .replace(viewBinding.frameFragment.id, FirstFragment())
                .commitAllowingStateLoss()
        }

        viewBinding.btnFragment2.setOnClickListener {
            supportFragmentManager
                .beginTransaction()
                .replace(viewBinding.frameFragment.id, SecondFragment())
                .commitAllowingStateLoss()
        }
    }
}