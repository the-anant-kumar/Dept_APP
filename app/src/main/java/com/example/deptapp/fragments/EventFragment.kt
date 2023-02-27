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
import com.example.deptapp.R
import com.example.deptapp.adapters.EventItemClicked
import com.example.deptapp.adapters.EventListAdapter
import com.example.deptapp.adapters.NoticeItemClicked
import com.example.deptapp.adapters.NoticeListAdapter
import com.example.deptapp.data.EventData
import com.example.deptapp.data.MySingleton
import com.example.deptapp.data.NoticeData
import com.example.deptapp.databinding.FragmentEventBinding


class EventFragment : Fragment(), NoticeItemClicked,EventItemClicked {
    lateinit var binding: FragmentEventBinding
    lateinit var noticeListAdapter: NoticeListAdapter
    lateinit var mEventListAdapter: EventListAdapter
//    lateinit var mNoticeArray: ArrayList<NoticeData>



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
        }else {
            fetchEventData()
            setUpEvent()
        }
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
    private fun setUpEvent() {
        binding.rvNotice.visibility=View.GONE
        binding.rvEvent.visibility=View.VISIBLE
        mEventListAdapter = EventListAdapter(this)
        binding.rvEvent.adapter=mEventListAdapter
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
                        noticeJsonObject.getString("batch"),
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
    private fun fetchEventData(){
        binding.eventLoader.visibility = View.VISIBLE
        val url = "https://ill-moth-stole.cyclic.app/api/event/fetch"
        val jsonObjectRequest = object : JsonObjectRequest(
            Method.GET, url, null,
            {
                binding.eventLoader.visibility = View.INVISIBLE
                val eventJsonArray = it.getJSONArray("response")
                val mEventArray = ArrayList<EventData>()
                for(i in 0 until eventJsonArray.length()){
                    val eventsJsonObject = eventJsonArray.getJSONObject(i)
                    val events = EventData(
                        eventsJsonObject.getString("_id"),
                        eventsJsonObject.getString("title"),
                        eventsJsonObject.getJSONArray("image"),
                        eventsJsonObject.getString("title"),
                        eventsJsonObject.getString("desc")
                    )
                    mEventArray.add(events)
                }
                mEventListAdapter.differ.submitList(mEventArray)
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

    override fun onItemClick(item: EventData) {
        val fragment = EventDetailsFragment()
        val bundle = Bundle()
        bundle.putString("title", item.eventTitle)
        bundle.putString("img1", item.eventImageUrl.getJSONObject(0)["imageurl"].toString())
        bundle.putString("img2", item.eventImageUrl.getJSONObject(1)["imageurl"].toString())
        bundle.putString("img3", item.eventImageUrl.getJSONObject(2)["imageurl"].toString())
        bundle.putString("desc", item.eventDesc)
        fragment.arguments = bundle
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame, fragment)
        transaction.commit()
    }
}
