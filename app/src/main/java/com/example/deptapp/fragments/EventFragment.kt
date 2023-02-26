package com.example.deptapp.fragments

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.toolbox.JsonObjectRequest
import com.example.deptapp.adapters.EventItemClicked
import com.example.deptapp.adapters.EventListAdapter
import com.example.deptapp.adapters.NoticeItemClicked
import com.example.deptapp.adapters.NoticeListAdapter
import com.example.deptapp.data.MySingleton
import com.example.deptapp.data.NoticeData
import com.example.deptapp.databinding.FragmentEventBinding


class EventFragment : Fragment(), NoticeItemClicked,EventItemClicked {
    lateinit var binding: FragmentEventBinding
    lateinit var noticeListAdapter: NoticeListAdapter
    lateinit var eventListAdapter: EventListAdapter
//    lateinit var mNoticeArray: ArrayList<NoticeData>

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
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentEventBinding.inflate(layoutInflater, container, false)
        val name = arguments?.getString("name")
//        val mNoticeArray = arguments?.getSerializable("noticeData") as ArrayList<*>

        if(name=="NOTICE") {
            fetchNoticeData()
            setUpNotice()
        }else
            setUpEvent()
        binding.tvEventFrag.text = name
        return binding.root
    }

    private fun setUpNotice() {
        binding.rvEvent.visibility=View.GONE
        binding.rvNotice.visibility=View.VISIBLE
        noticeListAdapter = NoticeListAdapter(this)
        binding.rvNotice.adapter=noticeListAdapter
        binding.rvNotice.layoutManager= LinearLayoutManager(binding.root.context)
    }
    private fun setUpEvent()
    {
        binding.rvNotice.visibility=View.GONE
        binding.rvEvent.visibility=View.VISIBLE
        eventListAdapter = EventListAdapter(itemLists,this)
        binding.rvEvent.adapter=eventListAdapter
        binding.rvEvent.layoutManager= LinearLayoutManager(binding.root.context)
    }

    private fun fetchNoticeData(){
        val url = "https://ill-moth-stole.cyclic.app/api/notice/fetch"
        val jsonObjectRequest = object : JsonObjectRequest(
            Method.GET, url, null,
            {
                binding.eventLoader.visibility=View.INVISIBLE
                val noticeJsonArray = it.getJSONArray("response")
                val mNoticeArray = ArrayList<NoticeData>()
                for(i in 0 until noticeJsonArray.length()){
                    val noticeJsonObject = noticeJsonArray.getJSONObject(i)
                    val notice = NoticeData(
                        noticeJsonObject.getString("title"),
                        noticeJsonObject.getString("pdfurl"),
                        noticeJsonObject.getString("date"),
                        noticeJsonObject.getString("pdfid")
                    )
                    mNoticeArray.add(notice)
                }
                noticeListAdapter.differ.submitList(mNoticeArray)
            },
            {
                Toast.makeText(context,"Error",Toast.LENGTH_LONG).show()
            }
        ){
        }
        MySingleton.getInstance(binding.root.context).addToRequestQueue(jsonObjectRequest)
    }

    override fun onItemClick(item: NoticeData) {
        val pdfUrl = item.pdfLink
        val builder = CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(binding.root.context, Uri.parse(pdfUrl))
    }

    override fun onItemClick(item: String) {
        Toast.makeText(binding.root.context,item,Toast.LENGTH_SHORT).show()
    }
}
