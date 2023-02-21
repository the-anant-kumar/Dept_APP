package com.example.deptapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.deptapp.R

class BooksListAdapter(private val itemsList: ArrayList<Triple<String, String, String>>) :
    RecyclerView.Adapter<BooksListAdapter.BooksListViewHolder>() {

    class BooksListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val bookTitle: TextView = itemView.findViewById(R.id.tvBookTitle)
        val bookAuthor: TextView = itemView.findViewById(R.id.tvAuthor)
        val bookCategory: TextView = itemView.findViewById(R.id.tvCategory)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BooksListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_book, parent, false)
        return BooksListViewHolder(view)
    }

    override fun onBindViewHolder(holder: BooksListViewHolder, position: Int) {
        holder.bookTitle.text=itemsList[position].first
        holder.bookAuthor.text=itemsList[position].second
        holder.bookCategory.text=itemsList[position].third
    }

    override fun getItemCount(): Int {
        return   itemsList.size
    }

}