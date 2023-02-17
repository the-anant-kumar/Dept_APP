package com.example.deptapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.deptapp.R

class NoticeBoardAdapter (val context: Context, private val itemsList: ArrayList<String>):  RecyclerView.Adapter<NoticeBoardAdapter.NoticeBoardViewHolder>(){

    class NoticeBoardViewHolder(view: View):RecyclerView.ViewHolder(view){
        val noticeImg: ImageView =view.findViewById(R.id.noticeImg)
        val noticeTitle: TextView =view.findViewById(R.id.noticeTitle)
        val noticeDate: TextView =view.findViewById(R.id.noticeDate)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoticeBoardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.notice_board,parent,false)
        return NoticeBoardViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoticeBoardViewHolder, position: Int) {
        Glide.with(context).load(R.drawable.img).into(holder.noticeImg)
        holder.noticeTitle.text=itemsList[position]
    }

    override fun getItemCount(): Int {
        return itemsList.size
    }
}