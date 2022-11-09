package com.example.umc_6th_mission

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class ChannelVPAdapter(fragmentActivity: ChannelFragment): FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> TabHomeFragment()
            1 -> TabVideoFragment()
            2 -> TabInfoFragment()
            else -> TabHomeFragment()
        }
    }
}