package com.example.deptapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.deptapp.R

class TeachersListAdapter(private val itemsList: ArrayList<String>) : RecyclerView.Adapter<TeachersListAdapter.TeachersListViewHolder>() {
    class TeachersListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val teacherImg: ImageView = itemView.findViewById(R.id.ivItemTeacher)
        val teacherName: TextView = itemView.findViewById(R.id.tvNameItemTeacher)
        val teacherDesignation: TextView = itemView.findViewById(R.id.tvDesignationItemTeacher)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeachersListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_teacher, parent, false)
        return TeachersListViewHolder(view)
    }

    override fun onBindViewHolder(holder: TeachersListViewHolder, position: Int) {
        holder.teacherName.text = itemsList[position]
        holder.teacherDesignation.text = itemsList[position]
    }

    override fun getItemCount(): Int {
        return itemsList.size
    }

}