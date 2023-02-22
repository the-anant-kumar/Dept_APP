package com.example.deptapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.example.deptapp.R
import com.example.deptapp.adapters.TeachersListAdapter
import com.example.deptapp.data.MySingleton
import com.example.deptapp.data.TeacherData
import com.example.deptapp.databinding.FragmentFacultyBinding

class FacultyFragment : Fragment() {

    lateinit var binding: FragmentFacultyBinding
    lateinit var teachersListAdapter: TeachersListAdapter

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

        setupTeachersList()

        return binding.root
    }

    private fun setupTeachersList() {
        teachersListAdapter = TeachersListAdapter(itemLists)
        binding.rvTeacher.adapter = teachersListAdapter
        binding.rvTeacher.layoutManager = LinearLayoutManager(binding.root.context)
    }
}