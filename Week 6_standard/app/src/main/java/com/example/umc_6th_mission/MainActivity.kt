package com.example.umc_6th_mission

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.umc_6th_mission.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        // 네비게이션바 아이콘 클릭 시 기본 color로 바뀌는 거 방지
        viewBinding.btmNavigationView.itemIconTintList = null

        // 가장 처음에 표시할 fragment 설정
        supportFragmentManager
            .beginTransaction()
            .replace(viewBinding.containerFragment.id, HomeFragment())
            .commitAllowingStateLoss()

        // 네비게이션바 클릭 이벤트 설정
        viewBinding.btmNavigationView.run {
            setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.btm_home -> {
                        supportFragmentManager
                            .beginTransaction()
                            .replace(viewBinding.containerFragment.id, HomeFragment())
                            .commitAllowingStateLoss()
                    }

                    R.id.btm_community -> {
                        supportFragmentManager
                            .beginTransaction()
                            .replace(viewBinding.containerFragment.id, CommunityFragment())
                            .commitAllowingStateLoss()
                    }

                    R.id.btm_channel -> {
                        supportFragmentManager
                            .beginTransaction()
                            .replace(viewBinding.containerFragment.id, ChannelFragment())
                            .commitAllowingStateLoss()
                    }

                    R.id.btm_inventory -> {
                        supportFragmentManager
                            .beginTransaction()
                            .replace(viewBinding.containerFragment.id, InventoryFragment())
                            .commitAllowingStateLoss()
                    }
                }
                true
            }

            // 초기 선택 item
            selectedItemId = R.id.btm_home
        }
    }
}
