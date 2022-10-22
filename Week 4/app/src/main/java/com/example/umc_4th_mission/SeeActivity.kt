package com.example.umc_4th_mission

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.viewbinding.ViewBinding
import com.example.umc_4th_mission.databinding.ActivityMainBinding
import com.example.umc_4th_mission.databinding.ActivitySeeBinding

class SeeActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivitySeeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivitySeeBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        val bundle = intent.extras

        if (bundle != null) {
            val content = bundle.getString("memoContent", "")
            val colormemo = bundle.getString("textColor")

            if (colormemo == null) {
                viewBinding.showMemo.text = content
            }
            else {
                if (colormemo == "Red") {
                    viewBinding.showMemo.text = content
                    viewBinding.showMemo.setTextColor(ContextCompat.getColor(applicationContext!!, R.color.red))
                }
                else if (colormemo == "Blue") {
                    viewBinding.showMemo.text = content
                    viewBinding.showMemo.setTextColor(ContextCompat.getColor(applicationContext!!, R.color.blue))
                }
                else if (colormemo == "Yellow") {
                    viewBinding.showMemo.text = content
                    viewBinding.showMemo.setTextColor(ContextCompat.getColor(applicationContext!!, R.color.yellow))
                }
            }
        }
    }


}