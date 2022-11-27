package com.example.umc_5th_mission

import android.view.View
import android.view.View.OnLongClickListener
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {

    var view : View = v

    fun bind(item: memoData) {
        view.findViewById<TextView>(R.id.memoTitle).text = item.memoTitle
        view.findViewById<TextView>(R.id.memoContent).text = item.memoContent
    }

    fun bindLong(item: memoData) {
        view.findViewById<TextView>(R.id.memoTitle).text = item.memoTitle
        view.findViewById<TextView>(R.id.memoContent).text = item.memoContent
    }

}
