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
import com.example.deptapp.data.EventData
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
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_event, parent, false)
        return EventViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val event = differ.currentList[position]
        val imageUrl = event.eventImageUrl.getJSONObject(0)["imageurl"]

        Glide.with(holder.itemView)
            .load(imageUrl)
            .into(holder.eventImage)
        holder.eventTitle.text = event.eventTitle
        holder.eventDate.text = event.eventTime

        holder.itemView.setOnClickListener {
            listener.onItemClick(items[position])
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}

interface EventItemClicked {
    fun onItemClick(item: String)
}