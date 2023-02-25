package com.example.deptapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.deptapp.R
import com.example.deptapp.adapters.EventListAdapter
import com.example.deptapp.adapters.NoticeListAdapter
import com.example.deptapp.databinding.FragmentEventBinding


class EventFragment : Fragment() {

    lateinit var binding: FragmentEventBinding
    lateinit var noticeListAdapter: NoticeListAdapter
    lateinit var eventListAdapter: EventListAdapter

    var itemLists = arrayListOf(
        "Minutes of the 141th Academic Council Meeting",
        "Minutes of the 141th Academic Council Meeting",
        "Minutes of the 141th Academic Council Meeting",
        "Minutes of the 141th Academic Council Meeting",
        "Minutes of the 141th Academic Council Meeting",
        "Minutes of the 141th Academic Council Meeting",
        "Minutes of the 141th Academic Council Meeting",
        "Minutes of the 141th Academic Council Meeting",
        "Minutes of the 141th Academic Council Meeting",
        "Minutes of the 141th Academic Council Meeting",
        "Minutes of the 141th Academic Council Meeting",
        "Minutes of the 141th Academic Council Meeting",
        "Minutes of the 141th Academic Council Meeting",
        "Minutes of the 141th Academic Council Meeting",
        "Minutes of the 141th Academic Council Meeting",
        "Minutes of the 141th Academic Council Meeting",
        "Minutes of the 141th Academic Council Meeting",
        "Minutes of the 141th Academic Council Meeting",
        "Minutes of the 141th Academic Council Meeting",
        "Minutes of the 141th Academic Council Meeting"
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEventBinding.inflate(layoutInflater, container, false)
        val name = arguments?.getString("name")
        if(name=="NOTICE")
            setUpNotice()
        else
            setUpEvent()
        binding.tvEventFrag.text = name
        return binding.root
    }

    private fun setUpNotice() {
        binding.rvEvent.visibility=View.GONE
        binding.rvNotice.visibility=View.VISIBLE
        noticeListAdapter = NoticeListAdapter(itemLists)
        binding.rvNotice.adapter=noticeListAdapter
        binding.rvNotice.layoutManager= LinearLayoutManager(binding.root.context)
    }
    private fun setUpEvent()
    {
        binding.rvNotice.visibility=View.GONE
        binding.rvEvent.visibility=View.VISIBLE
        eventListAdapter = EventListAdapter(itemLists)
        binding.rvEvent.adapter=eventListAdapter
        binding.rvEvent.layoutManager= LinearLayoutManager(binding.root.context)
    }
}