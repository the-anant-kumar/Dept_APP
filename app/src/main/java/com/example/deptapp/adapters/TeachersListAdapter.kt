package com.example.deptapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.deptapp.R

class TeachersListAdapter(private val itemsList: ArrayList<Triple<String, String, String>>) :
    RecyclerView.Adapter<TeachersListAdapter.TeachersListViewHolder>() {
    class TeachersListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val teacherImg: ImageView = itemView.findViewById(R.id.ivItemTeacher)
        val teacherName: TextView = itemView.findViewById(R.id.tvNameItemTeacher)
        val teacherDesignation: TextView = itemView.findViewById(R.id.tvDesignationItemTeacher)
        val teacherEmail: TextView = itemView.findViewById(R.id.tvEmailItemTeacher)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeachersListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_teacher, parent, false)
        return TeachersListViewHolder(view)
    }

    override fun onBindViewHolder(holder: TeachersListViewHolder, position: Int) {
        holder.teacherName.text = itemsList[position].first
        holder.teacherDesignation.text = itemsList[position].second
        holder.teacherEmail.text = itemsList[position].third
        Glide.with(holder.itemView)
            .load("https://thumbs.dreamstime.com/b/businessman-profile-icon-male-portrait-flat-design-vector-illustration-47075259.jpg")
            .into(holder.teacherImg)
    }

    override fun getItemCount(): Int {
        return itemsList.size
    }

}