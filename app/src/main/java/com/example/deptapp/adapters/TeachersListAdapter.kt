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
import com.example.deptapp.data.TeacherData

class TeachersListAdapter(private val listener: TeacherItemClicked) :
    RecyclerView.Adapter<TeachersListAdapter.TeachersListViewHolder>() {
    class TeachersListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val teacherImg: ImageView = itemView.findViewById(R.id.ivTeacherPopup)
        val teacherName: TextView = itemView.findViewById(R.id.tvNameTeacherPopup)
        val teacherDesignation: TextView = itemView.findViewById(R.id.tvDesignationTeacherPopup)
        val teacherEmail: TextView = itemView.findViewById(R.id.tvGenderTeacherPopup)
    }

    private val differCallback = object : DiffUtil.ItemCallback<TeacherData>() {
        override fun areItemsTheSame(oldItem: TeacherData, newItem: TeacherData): Boolean {
            return oldItem.teacherEmail == newItem.teacherEmail
        }

        override fun areContentsTheSame(oldItem: TeacherData, newItem: TeacherData): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeachersListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_teacher, parent, false)
        return TeachersListViewHolder(view)
    }

    override fun onBindViewHolder(holder: TeachersListViewHolder, position: Int) {
        val teacher = differ.currentList[position]

        Glide.with(holder.itemView)
            .load("https://thumbs.dreamstime.com/b/businessman-profile-icon-male-portrait-flat-design-vector-illustration-47075259.jpg")
            .into(holder.teacherImg)
        holder.teacherName.text = teacher.teacherName
        holder.teacherDesignation.text = teacher.teacherDesignation
        holder.teacherEmail.text = teacher.teacherEmail
        holder.itemView.setOnClickListener {
            listener.onItemClick(teacher)
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

}

interface TeacherItemClicked{
    fun onItemClick(item: TeacherData)
}