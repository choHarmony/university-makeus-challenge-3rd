package com.example.umc_5th_mission

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.nio.file.Files.size

class RecyclerViewAdapter(): RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.rv_item, parent, false)
        return RecyclerViewViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecyclerViewViewHolder, position: Int) {
        //holder.bind(recyclerViewItems[position])
    }

    override fun getItemCount(): Int {
        //return recyclerViewItems.size
        return 100
    }

    // 이거 추가하면서 아이템 재사용 방지할 수 있었음
    override fun getItemViewType(position: Int): Int {
        return position
    }

    inner class RecyclerViewViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        private val pfName: TextView = itemView.findViewById(R.id.profileName)
        private val stMessage: TextView = itemView.findViewById(R.id.statusMessage)

        fun bind(recyclerViewItem: RecyclerViewItem) {
            pfName.text = recyclerViewItem.pfName
            stMessage.text = recyclerViewItem.statMessage
        }
    }
}