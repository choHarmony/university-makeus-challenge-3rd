package com.example.umc_5th_mission

import android.view.View
import android.view.View.OnClickListener
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {

    var view : View = v

    fun bind(item: memoData, onClickListener: View.OnClickListener) {
        view.findViewById<TextView>(R.id.memoTitle).text = item.memoTitle
        view.findViewById<TextView>(R.id.memoContent).text = item.memoContent
        view.findViewById<LinearLayout>(R.id.mRootView).setOnClickListener(onClickListener)
    }

}