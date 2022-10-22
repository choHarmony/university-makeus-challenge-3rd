package com.example.umc_4th_mission

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.RadioButton
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.core.graphics.toColorInt
import com.example.umc_4th_mission.databinding.ActivityMainBinding
import com.example.umc_4th_mission.databinding.ActivityPaintBinding
import com.example.umc_4th_mission.databinding.ActivitySelectBinding

class PaintActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityPaintBinding
    lateinit var memoText: String
    lateinit var selectedValue: String

    fun onRadioButtonClicked(view: View) {
        if (view is RadioButton) {
            val checked = view.isChecked

            when (view.getId()) {
                R.id.btnRed -> {
                    if (checked) {
                        viewBinding.putMemo.setTextColor(ContextCompat.getColor(applicationContext!!, R.color.red))
                        selectedValue = "Red"
                    }
                }

                R.id.btnBlue -> {
                    if (checked) {
                        viewBinding.putMemo.setTextColor(ContextCompat.getColor(applicationContext!!, R.color.blue))
                        selectedValue = "Blue"
                    }
                }

                R.id.btnYellow -> {
                    if (checked) {
                        viewBinding.putMemo.setTextColor(ContextCompat.getColor(applicationContext!!, R.color.yellow))
                        selectedValue = "Yellow"
                    }
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivityPaintBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.btnAddMemo.setOnClickListener {
            val intent = Intent(this, SeeActivity::class.java)
            intent.putExtra("memoContent", viewBinding.putMemo.text.toString())
            intent.putExtra("textColor", selectedValue)

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