package com.example.umc_6th_mission

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.umc_6th_mission.databinding.FragmentHomeBinding
import com.example.umc_6th_mission.databinding.FragmentInventoryBinding

class InventoryFragment : Fragment() {

    private lateinit var viewBinding: FragmentInventoryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentInventoryBinding.inflate(layoutInflater)
        return viewBinding.root
    }
}