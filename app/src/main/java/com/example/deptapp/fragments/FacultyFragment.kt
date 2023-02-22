package com.example.deptapp.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.example.deptapp.adapters.TeachersListAdapter
import com.example.deptapp.data.MySingleton
import com.example.deptapp.data.TeacherData
import com.example.deptapp.databinding.FragmentFacultyBinding

class FacultyFragment : Fragment() {

    private lateinit var binding: FragmentFacultyBinding
    private lateinit var mTeachersListAdapter: TeachersListAdapter

    var itemLists = arrayListOf(
        Triple("Soumen Paul", "Head of Department", "spaul234@gmail.com"),
        Triple("Manasija Bhattacharya", "Asst. Professor", "mb332@gmail.com"),
        Triple("Tamosa Chakraborty", "Asst. Professor", "tamosa32it@gmail.com"),
        Triple("Banani Ghosh", "Asst. Professor", "banani23@gmail.com"),
        Triple("Ramkrishna Ghosh","Assoc. Professor", "ramkrishna@gmail.com"),
        Triple("Pijush Bair","Asst. Professor", "pbairi@gmail.com"),
        Triple("Debolina Ghosh","Asst. Professor", "dghosh@gmail.com"),
        Triple("Arundhati Bhowal", "Asst. Professor","ab23@gmail.com"),
        Triple("Bidyut Das ", "Assoc. Professor", "bidyut23@gmail.com"),
        Triple("Moumita Ghosh", "Asst. Professor", "mgh@gmail.com")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFacultyBinding.inflate(layoutInflater, container, false)

        fetchData()
        setupTeachersList()

        return binding.root
    }

    private fun setupTeachersList() {
        mTeachersListAdapter = TeachersListAdapter()
        binding.rvTeacher.adapter = mTeachersListAdapter
        binding.rvTeacher.layoutManager = LinearLayoutManager(binding.root.context)
    }

    private fun fetchData(){
        binding.teacherLoader.visibility = View.VISIBLE
        val url = "https://ill-moth-stole.cyclic.app/api/teacher/fetch"
        val jsonObjectRequest = object : JsonObjectRequest(
            Request.Method.GET, url, null,
            {
                binding.teacherLoader.visibility = View.INVISIBLE
                val teachersJsonArray = it.getJSONArray("tres")
                val mTeachersArray = ArrayList<TeacherData>()
                for(i in 0 until teachersJsonArray.length()){
                    val teachersJsonObject = teachersJsonArray.getJSONObject(i)
                    val teachers = TeacherData(
                        teachersJsonObject.getString("name"),
                        teachersJsonObject.getString("filename"),
                        teachersJsonObject.getString("designation"),
                        teachersJsonObject.getString("email"),
                        teachersJsonObject.getString("mobile"),
                        teachersJsonObject.getString("gender"),
                        teachersJsonObject.getString("education")
                    )
                    mTeachersArray.add(teachers)
                }
                mTeachersListAdapter.differ.submitList(mTeachersArray)
//                mAdapter.updateNews(mTeachersArray)
            },
            {
                Log.d("Error: ", it.toString())
            }
        ){
        }
        MySingleton.getInstance(binding.root.context).addToRequestQueue(jsonObjectRequest)
    }
}