package com.example.deptapp.fragments

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabsIntent
import androidx.fragment.app.Fragment
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.example.deptapp.data.MySingleton
import com.example.deptapp.data.RoutineData
import com.example.deptapp.data.SyllabusData
import com.example.deptapp.databinding.FragmentAcademicsBinding


class AcademicsFragment : Fragment() {

    private lateinit var binding: FragmentAcademicsBinding
    val mRoutineArray = ArrayList<RoutineData>()
    val mSyllabusArray = ArrayList<SyllabusData>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAcademicsBinding.inflate(layoutInflater, container, false)
        fetchDataRoutine()
        fetchDataSyllabus()
        return binding.root
    }

    private fun fetchDataRoutine(){
        val url = "https://ill-moth-stole.cyclic.app/api/routine/fetch"
        val jsonObjectRequest = object : JsonObjectRequest(
            Request.Method.GET, url, null,
            {
                val routineJsonArray = it.getJSONArray("response")
                for(i in 0 until routineJsonArray.length()){
                    val routineJsonObject = routineJsonArray.getJSONObject(i)
                    val routine = RoutineData(
                        routineJsonObject.getString("batch"),
                        routineJsonObject.getString("pdfurl")
                    )
                    mRoutineArray.add(routine)
                }

                binding.btnRoutinesForthYear.setOnClickListener {
                    val pdfurl = mRoutineArray[0].pdfurl
                    val builder = CustomTabsIntent.Builder()
                    val customTabsIntent = builder.build()
                    customTabsIntent.launchUrl(binding.root.context, Uri.parse(pdfurl))
                }
                binding.btnRoutinesThirdYear.setOnClickListener {
                    val pdfurl = mRoutineArray[1].pdfurl
                    val builder = CustomTabsIntent.Builder()
                    val customTabsIntent = builder.build()
                    customTabsIntent.launchUrl(binding.root.context, Uri.parse(pdfurl))
                }
                binding.btnRoutinesSecondYear.setOnClickListener {
                    val pdfurl = mRoutineArray[2].pdfurl
                    val builder = CustomTabsIntent.Builder()
                    val customTabsIntent = builder.build()
                    customTabsIntent.launchUrl(binding.root.context, Uri.parse(pdfurl))
                }
                binding.btnRoutinesFirstYear.setOnClickListener {
                    val pdfurl = mRoutineArray[3].pdfurl
                    val builder = CustomTabsIntent.Builder()
                    val customTabsIntent = builder.build()
                    customTabsIntent.launchUrl(binding.root.context, Uri.parse(pdfurl))
                }
            },
            {
                Log.d("Error: ", it.toString())
            }
        ){
        }
        MySingleton.getInstance(binding.root.context).addToRequestQueue(jsonObjectRequest)
    }

    private fun fetchDataSyllabus(){
        val url = "https://ill-moth-stole.cyclic.app/api/syllabus/fetch"
        val jsonObjectRequest = object : JsonObjectRequest(
            Request.Method.GET, url, null,
            {
                val syllabusJsonArray = it.getJSONArray("response")
                for(i in 0 until syllabusJsonArray.length()){
                    val syllabusJsonObject = syllabusJsonArray.getJSONObject(i)
                    val syllabus = SyllabusData(
                        syllabusJsonObject.getString("batch"),
                        syllabusJsonObject.getString("pdfurl")
                    )
                    mSyllabusArray.add(syllabus)
                }

                binding.btnSyllabusFourthYear.setOnClickListener {
                    val pdfurl = mSyllabusArray[0].pdfurl
                    val builder = CustomTabsIntent.Builder()
                    val customTabsIntent = builder.build()
                    customTabsIntent.launchUrl(binding.root.context, Uri.parse(pdfurl))
                }
                binding.btnSyllabusThirdYear.setOnClickListener {
                    val pdfurl = mSyllabusArray[1].pdfurl
                    val builder = CustomTabsIntent.Builder()
                    val customTabsIntent = builder.build()
                    customTabsIntent.launchUrl(binding.root.context, Uri.parse(pdfurl))
                }
                binding.btnSyllabusSecondYear.setOnClickListener {
                    val pdfurl = mSyllabusArray[2].pdfurl
                    val builder = CustomTabsIntent.Builder()
                    val customTabsIntent = builder.build()
                    customTabsIntent.launchUrl(binding.root.context, Uri.parse(pdfurl))
                }
                binding.btnSyllabusFirstYear.setOnClickListener {
                    val pdfurl = mSyllabusArray[3].pdfurl
                    val builder = CustomTabsIntent.Builder()
                    val customTabsIntent = builder.build()
                    customTabsIntent.launchUrl(binding.root.context, Uri.parse(pdfurl))
                }
            },
            {
                Log.d("Error: ", it.toString())
            }
        ){
        }
        MySingleton.getInstance(binding.root.context).addToRequestQueue(jsonObjectRequest)
    }

}