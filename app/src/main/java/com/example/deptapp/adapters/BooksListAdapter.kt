package com.example.deptapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.deptapp.R
import com.example.deptapp.data.BookData

class BooksListAdapter() :
    RecyclerView.Adapter<BooksListAdapter.BooksListViewHolder>() {

    private val items: ArrayList<BookData> = ArrayList()

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
        holder.bookTitle.text=items[position].bookTitle
        holder.bookAuthor.text=items[position].bookAuthor
        holder.bookCategory.text=items[position].bookCategory
    }

    override fun getItemCount(): Int {
        return   items.size
    }

    fun updateNews(updatedNews: ArrayList<BookData>)
    {
        items.clear()
        items.addAll(updatedNews)

        ///it's call again whole Adapter work
        notifyDataSetChanged()
    }

}