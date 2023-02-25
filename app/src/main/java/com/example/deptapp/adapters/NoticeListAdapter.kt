package com.example.deptapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.deptapp.R
import com.example.deptapp.data.NoticeData
import com.example.deptapp.data.TeacherData

class NoticeListAdapter(private val listener: NoticeItemClicked) :
    RecyclerView.Adapter<NoticeListAdapter.NoticeBoardViewHolder>() {

    class NoticeBoardViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val noticeTitle: TextView = view.findViewById(R.id.noticeTitle2)
        val noticeDate: TextView = view.findViewById(R.id.noticeDate2)
    }

    private val differCallback = object : DiffUtil.ItemCallback<NoticeData>() {
        override fun areItemsTheSame(oldItem: NoticeData, newItem: NoticeData): Boolean {
            return oldItem.noticeId == newItem.noticeId
        }

        override fun areContentsTheSame(oldItem: NoticeData, newItem: NoticeData): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoticeBoardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_notice, parent, false)
        return NoticeBoardViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoticeBoardViewHolder, position: Int) {
        val notice = differ.currentList[position]
        holder.noticeTitle.text = notice.noticeTitle
        holder.noticeDate.text = notice.noticeDate
        holder.itemView.setOnClickListener {
            listener.onItemClick(notice)
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}

interface NoticeItemClicked{
    fun onItemClick(item: NoticeData)
}