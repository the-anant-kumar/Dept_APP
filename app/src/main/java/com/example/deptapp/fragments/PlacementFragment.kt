package com.example.deptapp.fragments

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.android.volley.toolbox.JsonObjectRequest
import com.example.deptapp.R
import com.example.deptapp.adapters.CompanyAdapter
import com.example.deptapp.data.MySingleton
import com.example.deptapp.data.NoticeData
import com.example.deptapp.data.PlacementData
import com.example.deptapp.databinding.FragmentPlacementBinding
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.utils.ColorTemplate


class PlacementFragment : Fragment() {

    private lateinit var binding: FragmentPlacementBinding
    private lateinit var pieChart: PieChart
    private val mPlacementArray = ArrayList<PlacementData>()
    val TAG = "PLACEMENT FRAGMENT"

    var companyList= arrayListOf(
        "https://1000logos.net/wp-content/uploads/2020/05/Wipro-logo.jpg","https://upload.wikimedia.org/wikipedia/commons/thumb/a/a9/Amazon_logo.svg/603px-Amazon_logo.svg.png","https://1000logos.net/wp-content/uploads/2021/04/Accenture-logo.png","https://www.capgemini.com/wp-content/uploads/2022/05/Capgemini.gif",
        "https://upload.wikimedia.org/wikipedia/commons/1/15/Deloitte_Logo.png","https://upload.wikimedia.org/wikipedia/commons/thumb/b/b1/Tata_Consultancy_Services_Logo.svg/2560px-Tata_Consultancy_Services_Logo.svg.png",
        "https://cdn-ukwest.onetrust.com/logos/8d84415b-1b52-4bc4-8d5f-ca9a8eccaf8a/f7db8968-fc22-4620-92ac-dc05c5d2aa15/02460a41-565e-4cac-80a7-449bc8f77a72/logo-infosys.png","https://bl-i.thgim.com/public/news/vz9c2g/article65250306.ece/alternates/FREE_1200/cognizant.jpg",
        "https://upload.wikimedia.org/wikipedia/commons/thumb/0/05/PricewaterhouseCoopers_Logo.svg/2560px-PricewaterhouseCoopers_Logo.svg.png","https://www.freepnglogos.com/uploads/flipkart-logo-png/flipkart-logo-transparent-vector-3.png","https://pbs.twimg.com/profile_images/1493912218500407300/kplbf4Hm_400x400.jpg","https://sjbit.edu.in/wp-content/uploads/2021/08/mindtree-logo.jpg",
        "https://download.logo.wine/logo/Hexaware_Technologies/Hexaware_Technologies-Logo.wine.png","https://www.channelinsider.com/wp-content/uploads/2022/04/CI.TechMahindra.Profile-1024x1024.png","https://upload.wikimedia.org/wikipedia/commons/thumb/7/72/Effective_Programming_for_America_logo.svg/1200px-Effective_Programming_for_America_logo.svg.png",
        "https://res.cloudinary.com/skillmine/images/v1646038066/Logo/Logo-jpg?_i=AA","https://image4.owler.com/logo/pinclick_owler_20160226_110110_original.png","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTNI_zVLCrxjjbb6vMJN58-77pt4_Pk5YDR4STabjY36KEt1i68zP6khVkbpI06lD1MggI&usqp=CAU"
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPlacementBinding.inflate(layoutInflater, container, false)

        pieChart = binding.placementPieChart


        setupPieChart()
        fetchNoticeData()

        loadCompany()

        return binding.root
    }

    private fun fetchNoticeData(){
        val url = "https://ill-moth-stole.cyclic.app/api/placement/year"
        val jsonObjectRequest = object : JsonObjectRequest(
            Method.GET, url, null,
            {
                val placementJsonArray = it.getJSONArray("response")
//                for(i in 0 until placementJsonArray.length()){
                    val placementJsonObject = placementJsonArray.getJSONObject(0)
                    val placement = PlacementData(
                        placementJsonObject.getString("year"),
                        placementJsonObject.getJSONArray("records")
                    )
                    mPlacementArray.add(placement)
//                }
                loadPieChartData()
            },
            {
//                Toast.makeText(context,"Error", Toast.LENGTH_LONG).show()
                Log.d(TAG, "Error")
            }
        ){
        }
        MySingleton.getInstance(binding.root.context).addToRequestQueue(jsonObjectRequest)
    }

    private fun loadCompany() {
        binding.rvCompany.layoutManager=GridLayoutManager(activity as Context,3,GridLayoutManager.HORIZONTAL,false)
        binding.rvCompany.adapter=CompanyAdapter(companyList)

        val pagerSnapHelper = PagerSnapHelper()
        pagerSnapHelper.attachToRecyclerView(binding.rvCompany)
        binding.indicator.attachToRecyclerView(binding.rvCompany, pagerSnapHelper)
    }

    private fun setupPieChart() {
        pieChart.isDrawHoleEnabled = true
        pieChart.setUsePercentValues(true)
        pieChart.setEntryLabelTextSize(12f)
        pieChart.setEntryLabelColor(Color.BLACK)
        val r = resources.getFont(R.font.serif)
        pieChart.setCenterTextTypeface(r)
        pieChart.setCenterTextColor(Color.parseColor("#4E5180"))
        pieChart.setCenterTextSize(30f)
        pieChart.description.isEnabled = false
        pieChart.setExtraOffsets(5f,20f,5f,20f)

        val l = pieChart.legend
        l.isEnabled = false

    }

    private fun loadPieChartData() {

        pieChart.centerText = "BATCH\n${mPlacementArray[0].year}"
        val entries: ArrayList<PieEntry> = ArrayList()
        val count = ArrayList<Pair<Float,String>>()
        var totalCount = 0f
        for(i in 0 until mPlacementArray[0].placementRecord.length()) {
            val cnt = mPlacementArray[0].placementRecord.getJSONObject(i)["count"].toString()
            count.add(Pair(cnt.toFloat(), mPlacementArray[0].placementRecord.getJSONObject(i)["company"].toString()))
            totalCount += cnt.toFloat()
        }
        count.sortByDescending { it.first }


        val comp1 = count[0].first / totalCount
        val comp2 = count[1].first / totalCount
        val comp3 = count[2].first / totalCount
        val comp4 = count[3].first / totalCount
        val comp5 = count[4].first / totalCount
        val other = 1 - (comp1+comp2+comp3+comp4+comp5)

        entries.add(PieEntry(comp1, count[0].second))
        entries.add(PieEntry(comp2, count[1].second))
        entries.add(PieEntry(comp3, count[2].second))
        entries.add(PieEntry(comp4, count[3].second))
        entries.add(PieEntry(comp5, count[4].second))
        entries.add(PieEntry(other, "Others"))

        val colors: ArrayList<Int> = ArrayList()
        for (color in ColorTemplate.MATERIAL_COLORS) {
            colors.add(color)
        }

        for (color in ColorTemplate.VORDIPLOM_COLORS) {
            colors.add(color)
        }

        val dataSet = PieDataSet(entries, "Batch")
        dataSet.colors = colors

        val data = PieData(dataSet)
        data.setDrawValues(true)
        data.setValueFormatter(PercentFormatter(pieChart))
        data.setValueTextSize(12f)
        data.setValueTextColor(Color.BLACK)

        pieChart.data = data
        pieChart.invalidate()

        pieChart.animateY(1400, Easing.EaseInOutQuad)
    }

}