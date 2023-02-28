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
import com.example.deptapp.data.EventData
import com.example.deptapp.data.TeacherData

class EventList2Adapter(
    private val listener: EventItem2Clicked
) : RecyclerView.Adapter<EventList2Adapter.EventViewHolder>() {

    class EventViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val eventTitle: TextView = view.findViewById(R.id.tvEventTitle)
        val eventDate: TextView = view.findViewById(R.id.tvEventTime)
        val eventImage: ImageView = view.findViewById(R.id.imgEvent)
    }

    private val differCallback = object : DiffUtil.ItemCallback<EventData>() {
        override fun areItemsTheSame(oldItem: EventData, newItem: EventData): Boolean {
            return oldItem.eventID == newItem.eventID
        }

        override fun areContentsTheSame(oldItem: EventData, newItem: EventData): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_event2, parent, false)
        return EventViewHolder(view)
    }


    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val event = differ.currentList[position]
        val imageUrl = event.eventImageUrl.getJSONObject(0)["imageurl"]

        Glide.with(holder.itemView)
            .load(imageUrl)
            .into(holder.eventImage)
        holder.eventTitle.text = event.eventTitle
        holder.eventDate.text = event.eventTime.subSequence(0,10)

        holder.itemView.setOnClickListener {
            listener.onItemClick(event)
        }
    }
}

interface EventItem2Clicked {
    fun onItemClick(item: EventData)
}