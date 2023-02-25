package com.example.deptapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.deptapp.R

class NoticeListAdapter(private val itemsList: ArrayList<String>) :
    RecyclerView.Adapter<NoticeListAdapter.NoticeBoardViewHolder>() {

    class NoticeBoardViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val noticeTitle: TextView = view.findViewById(R.id.noticeTitle)
        val noticeDate: TextView = view.findViewById(R.id.noticeDate)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoticeBoardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_notice, parent, false)
        return NoticeBoardViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoticeBoardViewHolder, position: Int) {
        holder.noticeTitle.text = itemsList[position]
    }

    override fun getItemCount(): Int {
        return itemsList.size
    }
}