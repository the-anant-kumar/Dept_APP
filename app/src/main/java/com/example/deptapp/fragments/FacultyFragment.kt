package com.example.deptapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.deptapp.R
import com.example.deptapp.adapters.TeachersListAdapter
import com.example.deptapp.databinding.FragmentFacultyBinding

class FacultyFragment : Fragment() {

    lateinit var binding: FragmentFacultyBinding
    lateinit var teachersListAdapter: TeachersListAdapter
    var itemLists = arrayListOf(
        "Soumen Paul",
        "MB",
        "Tamosa Chokroborty",
        "Banani Ghosh",
        "Debolina Ghosh",
        "Bidyut Das",
        "Pijush Bairi",
        "Arundhati Bhowal",
        "Moumita Ghosh",
        "Anushree Pramanik"
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