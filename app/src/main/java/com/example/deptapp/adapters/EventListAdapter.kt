package com.example.deptapp.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.deptapp.R
import com.example.deptapp.data.TeacherData

class EventListAdapter(
    private val items: ArrayList<String>,
    private val listener: EventItemClicked
) : RecyclerView.Adapter<EventListAdapter.EventViewHolder>() {

    class EventViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val eventTitle: TextView = view.findViewById(R.id.tvEventTitle)
        val eventDate: TextView = view.findViewById(R.id.tvEventTime)
        val eventImage: ImageView = view.findViewById(R.id.imageEvent)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_event, parent, false)
        return EventViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load("https://firebasestorage.googleapis.com/v0/b/social-media-2-0.appspot.com/o/images%2Ffour-min.JPG?alt=media&token=ad0aace8-0686-4ef8-8852-6e3fb4867c72")
            .into(holder.eventImage)
        holder.eventTitle.text = items[position]
        holder.itemView.setOnClickListener {
            listener.onItemClick(items[position])
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}

interface EventItemClicked {
    fun onItemClick(item: String)
}