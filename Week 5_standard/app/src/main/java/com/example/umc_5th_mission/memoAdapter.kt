package com.example.umc_5th_mission

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


class memoAdapter(val memoList: ArrayList<memoData>) : RecyclerView.Adapter<ViewHolder>()
{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.memo_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.itemView.setOnClickListener {

        }
        val item = memoList[position]
        val adapter = this
        holder.apply {
            bind(item, View.OnClickListener {
                memoList.removeAt(adapterPosition)
                notifyItemChanged(adapterPosition)
                notifyItemRangeChanged(adapterPosition, memoList.size)
            })
        }


//        holder.apply {
//            bind(item, View.OnLongClickListener)
//        }
    }

    interface onItemClickListener {
        fun onClick(v: View, position: Int)
    }

    private lateinit var itemClickListener : onItemClickListener

    fun setItemClickListener(itemClickListener: onItemClickListener) {
        this.itemClickListener = itemClickListener
    }

    override fun getItemCount(): Int {
        return memoList.size
    }

    }

