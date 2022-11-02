package com.example.umc_5th_mission

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.umc_5th_mission.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        val recyclerViewItems: ArrayList<RecyclerViewItem> = ArrayList()

        val recyclerView = viewBinding.recyclerview
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = RecyclerViewAdapter()
        recyclerView.adapter = adapter
    }
}

data class RecyclerViewItem (
    val pfName: String = "",
    val statMessage: String = ""
)