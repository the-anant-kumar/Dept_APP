package com.example.deptapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.deptapp.R
import com.example.deptapp.data.BookData
import com.example.deptapp.data.TeacherData

class BooksListAdapter() :
    RecyclerView.Adapter<BooksListAdapter.BooksListViewHolder>() {

    class BooksListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val bookTitle: TextView = itemView.findViewById(R.id.tvBookTitle)
        val bookAuthor: TextView = itemView.findViewById(R.id.tvAuthor)
        val bookCategory: TextView = itemView.findViewById(R.id.tvCategory)
    }

    private val differCallback = object : DiffUtil.ItemCallback<BookData>() {
        override fun areItemsTheSame(oldItem: BookData, newItem: BookData): Boolean {
            return oldItem.bookTitle == newItem.bookTitle
        }

        override fun areContentsTheSame(oldItem: BookData, newItem: BookData): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BooksListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_book, parent, false)
        return BooksListViewHolder(view)
    }

    override fun onBindViewHolder(holder: BooksListViewHolder, position: Int) {
        val teacher = differ.currentList[position]
        holder.bookTitle.text=teacher.bookTitle
        holder.bookAuthor.text=teacher.bookAuthor
        holder.bookCategory.text=teacher.bookCategory
    }

    override fun getItemCount(): Int {
        return   differ.currentList.size
    }

}