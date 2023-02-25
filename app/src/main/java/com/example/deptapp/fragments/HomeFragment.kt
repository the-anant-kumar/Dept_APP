package com.example.deptapp.fragments

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.toolbox.JsonObjectRequest
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.deptapp.R
import com.example.deptapp.adapters.EventList2Adapter
import com.example.deptapp.data.MySingleton
import com.example.deptapp.data.NoticeData
import com.example.deptapp.data.TeacherData

import com.example.deptapp.adapters.*

import com.example.deptapp.databinding.FragmentHomeBinding
import com.google.android.material.navigation.NavigationView
import kotlinx.coroutines.*



class HomeFragment : Fragment(), EventItem2Clicked {
    lateinit var binding: FragmentHomeBinding
    lateinit var imageList: ArrayList<SlideModel>
    lateinit var eventListAdapter: EventList2Adapter
    val mNoticeArray = ArrayList<NoticeData>()


    var itemLists = arrayListOf(
        "Minutes of the 140th Academic Council Meeting",
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

        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        fetchNoticeData()
        setUpEvent()

        binding.btnWebsite.setOnClickListener {
            // custom browse
            val builder = CustomTabsIntent.Builder()
            val customTabsIntent = builder.build()
            customTabsIntent.launchUrl(binding.root.context, Uri.parse("https://hithaldia.ac.in/"))
        }

        binding.btnFaculity.setOnClickListener {
            val fragment= FacultyFragment()
            val transaction=requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.frame,fragment)
            transaction.commit()
            activity?.findViewById<NavigationView>(R.id.navigationView)?.setCheckedItem(R.id.faculty)
        }

        binding.btnAcademics.setOnClickListener {
            val fragment= AcademicsFragment()
            val transaction=requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.frame,fragment)
            transaction.commit()
            requireActivity().findViewById<NavigationView>(R.id.navigationView)?.setCheckedItem(R.id.academics)
        }

        binding.tvBtnEventViewMore.setOnClickListener {
            val bundle=Bundle()
            bundle.putString("name","EVENTS")
//            bundle.putSerializable("noticeData", mNoticeArray)
            val fragment = EventFragment()
            fragment.arguments=bundle
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.frame, fragment)
            transaction.commit()
            requireActivity().findViewById<NavigationView>(R.id.navigationView).checkedItem?.isChecked = false
        }
        binding.tvBtnNoticeViewMore.setOnClickListener {
            val bundle=Bundle()
            bundle.putString("name","NOTICE")
            val fragment = EventFragment()
            fragment.arguments=bundle
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.frame, fragment)
            transaction.commit()
            requireActivity().findViewById<NavigationView>(R.id.navigationView).checkedItem?.isChecked = false
        }
        binding.marqueeText.isSelected = true
        imageList = ArrayList()

        imageList.add(
            SlideModel(
                "https://www.admissionfever.com/Media/clgimg/gallery/2092_img7281787176718769.png",
                "Haldia Institute Of Technology"
            )
        )
        imageList.add(
            SlideModel(
                "https://firebasestorage.googleapis.com/v0/b/social-media-2-0.appspot.com/o/images%2Ffour-min.JPG?alt=media&token=ad0aace8-0686-4ef8-8852-6e3fb4867c72",
                "ACM Hack Track Workshop"
            )
        )
        imageList.add(
            SlideModel(
                "https://firebasestorage.googleapis.com/v0/b/social-media-2-0.appspot.com/o/images%2Ffour-min.JPG?alt=media&token=ad0aace8-0686-4ef8-8852-6e3fb4867c72",
                "ACM Members"
            )
        )
        imageList.add(
            SlideModel(
                "https://firebasestorage.googleapis.com/v0/b/social-media-2-0.appspot.com/o/images%2Fone-min.JPG?alt=media&token=c5e88727-c810-42d9-886f-9953073ec654",
                "Prize Distribution"
            )
        )
        imageList.add(
            SlideModel(
                "https://firebasestorage.googleapis.com/v0/b/social-media-2-0.appspot.com/o/images%2Ftwo.JPG?alt=media&token=059ccf95-ede1-4787-a833-caaec8e56a91",
                "Workshop"
            )
        )
        binding.imageSlider.setImageList(imageList, ScaleTypes.CENTER_CROP)
        return binding.root
    }

    private fun setUpEvent()
    {
        eventListAdapter = EventList2Adapter(itemLists,this)
        binding.rvEvents.adapter=eventListAdapter
        binding.rvEvents.layoutManager= LinearLayoutManager(binding.root.context,LinearLayoutManager.HORIZONTAL,false)
    }

    private fun fetchNoticeData(){
        val url = "https://ill-moth-stole.cyclic.app/api/notice/fetch"
        val jsonObjectRequest = object : JsonObjectRequest(
            Method.GET, url, null,
            {
                val noticeJsonArray = it.getJSONArray("response")
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
                setupNoticeHome()
            },
            {
                Toast.makeText(context,"Error", Toast.LENGTH_LONG).show()
            }
        ){
        }
        MySingleton.getInstance(binding.root.context).addToRequestQueue(jsonObjectRequest)
    }

    private fun setupNoticeHome() {
        binding.noticeTitle1.text = mNoticeArray[0].noticeTitle
        binding.noticeDate1.text = mNoticeArray[0].noticeDate
        binding.noticeClick1.setOnClickListener {
            val pdfUrl = mNoticeArray[0].pdfLink
            val builder = CustomTabsIntent.Builder()
            val customTabsIntent = builder.build()
            customTabsIntent.launchUrl(binding.root.context, Uri.parse(pdfUrl))
        }

        binding.noticeTitle2.text = mNoticeArray[1].noticeTitle
        binding.noticeDate2.text = mNoticeArray[1].noticeDate
        binding.noticeClick2.setOnClickListener {
            val pdfUrl = mNoticeArray[1].pdfLink
            val builder = CustomTabsIntent.Builder()
            val customTabsIntent = builder.build()
            customTabsIntent.launchUrl(binding.root.context, Uri.parse(pdfUrl))
        }

        binding.noticeTitle3.text = mNoticeArray[2].noticeTitle
        binding.noticeDate3.text = mNoticeArray[2].noticeDate
        binding.noticeClick3.setOnClickListener {
            val pdfUrl = mNoticeArray[2].pdfLink
            val builder = CustomTabsIntent.Builder()
            val customTabsIntent = builder.build()
            customTabsIntent.launchUrl(binding.root.context, Uri.parse(pdfUrl))
        }

        binding.noticeTitle4.text = mNoticeArray[3].noticeTitle
        binding.noticeDate4.text = mNoticeArray[3].noticeDate
        binding.noticeClick4.setOnClickListener {
            val pdfUrl = mNoticeArray[3].pdfLink
            val builder = CustomTabsIntent.Builder()
            val customTabsIntent = builder.build()
            customTabsIntent.launchUrl(binding.root.context, Uri.parse(pdfUrl))
        }

        binding.noticeTitle5.text = mNoticeArray[4].noticeTitle
        binding.noticeDate5.text = mNoticeArray[4].noticeDate
        binding.noticeClick5.setOnClickListener {
            val pdfUrl = mNoticeArray[5].pdfLink
            val builder = CustomTabsIntent.Builder()
            val customTabsIntent = builder.build()
            customTabsIntent.launchUrl(binding.root.context, Uri.parse(pdfUrl))
        }
    }
    override fun onItemClick(item: String) {
        Toast.makeText(binding.root.context,item, Toast.LENGTH_SHORT).show()
    }
}