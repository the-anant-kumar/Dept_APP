package com.example.deptapp.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.deptapp.R

class CompanyAdapter(private val itemList:ArrayList<String>):RecyclerView.Adapter<CompanyAdapter.CompanyViewHolder>() {

    class CompanyViewHolder(view: View):RecyclerView.ViewHolder(view){
        val imgCompany:ImageView=view.findViewById(R.id.imageCompany)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompanyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_company, parent, false)
        return CompanyViewHolder(view)
    }

    override fun onBindViewHolder(holder: CompanyViewHolder, position: Int) {
        Glide.with(holder.itemView).load(itemList[position]).into(holder.imgCompany)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}