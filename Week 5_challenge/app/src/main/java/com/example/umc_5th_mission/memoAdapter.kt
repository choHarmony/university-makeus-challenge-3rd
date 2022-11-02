package com.example.umc_5th_mission

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class memoAdapter(val memoList: ArrayList<memoData>) : RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.memo_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = memoList[position]
        val adapter = this

        holder.itemView.setOnClickListener {
            itemClickListener.onClick(it, position)
        }
        holder.apply {
            bind(item)
        }

        holder.itemView.setOnLongClickListener {
            itemLongClickListener.onLongClick(it, position)
            true
        }
        holder.apply {
            bindLong(item)
        }
    }

    override fun getItemCount(): Int {
        return memoList.size
    }


    // click event 관련
    interface onItemClickListener {
        fun onClick(v: View, position: Int)
    }

    private lateinit var itemClickListener : onItemClickListener

    fun setItemClickListener(itemClickListener: onItemClickListener) {
        this.itemClickListener = itemClickListener
    }


    interface onItemLongClickListener {
        fun onLongClick(v: View, position: Int)
    }

    private lateinit var itemLongClickListener: onItemLongClickListener

    fun setItemLongClickListener(itemLongClickListener: onItemLongClickListener) {
        this.itemLongClickListener = itemLongClickListener
    }

    }