package com.example.deptapp.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.browser.customtabs.CustomTabsIntent
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.example.deptapp.data.MySingleton
import com.example.deptapp.data.StudentData
import com.example.deptapp.databinding.FragmentStudentsBinding
import com.example.deptapp.util.ConnectionManager


class StudentsFragment : Fragment() {

    private lateinit var binding: FragmentStudentsBinding
    val mStudentsArray = ArrayList<StudentData>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStudentsBinding.inflate(layoutInflater, container, false)
        fetchData()
        return binding.root
    }

    private fun fetchData(){
//        binding.studentLoader.visibility = View.VISIBLE
        val url = "https://ill-moth-stole.cyclic.app/api/student/fetch"
        val jsonObjectRequest = object : JsonObjectRequest(
            Method.GET, url, null,
            {
//                binding.studentLoader.visibility = View.INVISIBLE
                binding.shimmerStudent.stopShimmer()
                binding.shimmerStudent.visibility = View.GONE
                binding.cvFourthYear.visibility = View.VISIBLE
                binding.cvThirdYear.visibility = View.VISIBLE
                binding.cvSecondYear.visibility = View.VISIBLE
                binding.cvFirstYear.visibility = View.VISIBLE
                val studentsJsonArray = it.getJSONArray("response")
                for(i in 0 until studentsJsonArray.length()){
                    val studentsJsonObject = studentsJsonArray.getJSONObject(i)
                    val students = StudentData(
                        studentsJsonObject.getString("batch"),
                        studentsJsonObject.getString("pdfurl")
                    )
                    mStudentsArray.add(students)
                }

                binding.tvFourthYear.text = mStudentsArray[0].batch
                binding.tvThirdYear.text = mStudentsArray[1].batch
                binding.tvSecondYear.text = mStudentsArray[2].batch
                binding.tvFirstYear.text = mStudentsArray[3].batch
                binding.btnShowFourthYear.setOnClickListener {
                    val pdfUrl = mStudentsArray[0].pdfurl
                    val builder = CustomTabsIntent.Builder()
                    val customTabsIntent = builder.build()
                    customTabsIntent.launchUrl(binding.root.context, Uri.parse(pdfUrl))
                }
                binding.btnShowThirdYear.setOnClickListener {
                    val pdfUrl = mStudentsArray[1].pdfurl
                    val builder = CustomTabsIntent.Builder()
                    val customTabsIntent = builder.build()
                    customTabsIntent.launchUrl(binding.root.context, Uri.parse(pdfUrl))
                }
                binding.btnShowSecondYear.setOnClickListener {
                    val pdfUrl = mStudentsArray[2].pdfurl
                    val builder = CustomTabsIntent.Builder()
                    val customTabsIntent = builder.build()
                    customTabsIntent.launchUrl(binding.root.context, Uri.parse(pdfUrl))
                }
                binding.btnShowFirstYear.setOnClickListener {
                    val pdfUrl = mStudentsArray[3].pdfurl
                    val builder = CustomTabsIntent.Builder()
                    val customTabsIntent = builder.build()
                    customTabsIntent.launchUrl(binding.root.context, Uri.parse(pdfUrl))
                }
            },
            {
                Toast.makeText(context,"Error",Toast.LENGTH_LONG).show()
            }
        ){
        }
        MySingleton.getInstance(binding.root.context).addToRequestQueue(jsonObjectRequest)
    }

}