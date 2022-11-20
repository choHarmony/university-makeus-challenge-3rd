package com.example.umc_7th_mission

import android.media.AudioManager
import android.media.SoundPool
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.annotation.RequiresApi
import com.example.umc_7th_mission.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    var mPool: SoundPool? = null
    var mSoundFile: Int = 0

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var handler = Handler(Looper.getMainLooper())

        // soundpool
        mPool = SoundPool(1, AudioManager.STREAM_MUSIC, 0)
        mSoundFile = mPool!!.load(this, R.raw.sound, 1)

        // 시작 버튼 클릭 이벤트
        binding.startBtn.setOnClickListener {

            // 분, 초 입력하는 EditText가 비었으면 0으로 설정하고, 사용자에게 보일 TextView editText 값으로 설정
            if (binding.minuteEdit.text.toString().length == 0) {
                binding.minuteNumber.setText("00")
            }
            else {
                binding.minuteNumber.setText("%02d".format(binding.minuteEdit.text.toString().toInt()))
            }

            if (binding.secEdit.text.toString().length == 0) {
                binding.secNumber.setText("00")
            }
            else {
                binding.secNumber.setText("%02d".format(binding.secEdit.text.toString().toInt()))
            }

            // editText에 입력한 분/초 받아오기
            var minute = binding.minuteNumber.text.toString().toInt()
            var sec = binding.secNumber.text.toString().toInt()

            // editText들 invisible
            binding.minuteEdit.visibility = View.INVISIBLE
            binding.secEdit.visibility = View.INVISIBLE

            // textView들 visible
            binding.minuteNumber.visibility = View.VISIBLE
            binding.secNumber.visibility = View.VISIBLE

            // thread 시작
            Thread() {

                for (i in sec-1 downTo 0) {
                    Thread.sleep(1000)
                    handler.post {
                        binding.secNumber.text = "%02d".format(i.toString().toInt())
                    }
                }

                Thread.sleep(1000)

                for (j in minute-1 downTo 0) {

                    handler.post {
                        binding.minuteNumber.text = "%02d".format(j.toString().toInt())
                        binding.secNumber.text = "59"
                    }

                    for (k in 58 downTo 0) {
                        Thread.sleep(1000)
                        handler.post {
                            binding.secNumber.text = "%02d".format(k.toString().toInt())
                        }
                    }
                    Thread.sleep(1000)
                }
                mPool!!.play(mSoundFile, 1f, 1f, 0, 0, 1F)

            }.start()

        }

        // 정지 버튼 클릭 이벤트
        binding.resetBtn.setOnClickListener {

            // editText들 visible
            binding.minuteEdit.visibility = View.VISIBLE
            binding.secEdit.visibility = View.VISIBLE

            // textView들 invisible
            binding.minuteNumber.visibility = View.INVISIBLE
            binding.secNumber.visibility = View.INVISIBLE

            // editText 초기화
            binding.secEdit.setText(null)
            binding.minuteEdit.setText(null)

        }
    }
}